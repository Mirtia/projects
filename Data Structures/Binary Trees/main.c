/*************************************************************
 * @file   main.c                                            *
 * @author Papaioannou Antonis 						  	     *
 *                                                    	     *
 * @brief  Source file for the needs of CS-240b project 2018 *
 ************************************************************/

#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <math.h>

#include "Shopping.h"

#define BUFFER_SIZE 1024  /* Maximum length of a line in input file */

/* Uncomment the following line to enable debugging prints 
 * or comment to disable it */
#define DEBUG

#ifdef DEBUG
#define DPRINT(...) fprintf(stderr, __VA_ARGS__);
#else  /* DEBUG */
#define DPRINT(...)
#endif /* DEBUG */

/**Myrsini Gkolemi
  *csd 3929
  **/



/**
 * @brief Optional function to initialize data structures that 
 *        need initialization
 *
 * @return 1 on success
 *         0 on failure
 */
int Initialize (void)
{
    int i;
    shopRoot = &shopSentinel;
    for(i=0;i<5;i++)
        ShopTypes[i] = NULL;
    /*input not yet read*/
    customers_hash = NULL;
    return 1;
}
/*The following functions are prints for each event indivindually*/
int NPrintShoppingTree(struct Shop * traverse,int sid,int type)
{
    if(traverse==&shopSentinel) return 0;
    NPrintShoppingTree(traverse->lc,sid,type);
    printf("< %d , %d >,",traverse->sid,traverse->type);
    NPrintShoppingTree(traverse->rc,sid,type);
    return 1;
}

int PrintR(int sid,int type)
{
    struct Shop * traverseShop;
    printf("Event R:\n");
    printf("R <%d> <%d>\n",sid,type);
    /*print shop without the product lists*/
    printf("Shops = ");
    traverseShop = shopRoot;
    NPrintShoppingTree(traverseShop,sid,type);
    printf("\nDONE\n");
    return 1; 
}

int PrintS(int sid,int pid,int quantity, int price)
{
    printf("Event S:\n");
    printf("S <%d> <%d> <%d> <%d>\n",sid,pid,quantity,price);
    PrintShops();
    return 1;
}

int PrintC(int cid)
{
    int i;
    printf("Event C:\n");
    printf("C <%d>\n",cid);
    printf("Customers = ");
    for(i=0;i<primes_g[hash_size];i++)  if(customers_hash[i]!=NULL) printf("< cid = %d >,",customers_hash[i]->cid);
    printf("\nDONE\n");
    return 1;
}

int PrintL(int cid,int pid,int quantity)
{
    printf("Event L:\n");
    printf("L <%d> <%d> <%d>\n",cid,pid,quantity);
    PrintCustomers();
    return 1;
}

int PrintD(int cid,int pid)
{
    printf("Event D:\n");
    printf("D <%d> <%d>\n",cid,pid);

    PrintCustomers();
    return 1;
}

int PrintG(int cid , struct Customer * customer)
{

    printf("Event G:\n");
    printf("G <%d>\n",cid);
    /*print customer shopping list*/
    printf("Customer %d = ",cid);
    PrintShoppingList(customer->shopping_tree);
    printf("\n");
    PrintShops();
    return 1;
}

int PrintM( int cid1 , int cid2,struct Customer * firstCustomer)
{
    struct C_Product * traverseList;
    printf("Event M:\n");
    printf("M <%d><%d>\n",cid1,cid2);
    traverseList = firstCustomer->shopping_tree;
    PrintShoppingList(traverseList);
    printf("\nDONE\n");
    return 1;
}

/*---------------------------------------------------------------*/
int PrintProductShops(struct Shop * target)
{
   struct Product  * product;
   product = target->products;
   printf("Shop %d: ",target->sid);
   while(product!=NULL)
   {
       printf("< %d , %d , %d >,",product->pid,
               product->qty,product->price);
       product = product->next;
   }
   printf("\n");
   return 1;
}


int PrintShoppingTree(struct Shop * traverse)
{
  if(traverse==&shopSentinel) return 0;
  PrintShoppingTree(traverse->lc);
  PrintProductShops(traverse);
  PrintShoppingTree(traverse->rc);
  return 1;
}

/**
 * @brief Use ino-order traversing to print contents of Customer shopping list
 *
 * @return 1 on success
 *         0 on failure
 */
int PrintShoppingList(struct C_Product * traverse)
{

    if (traverse == NULL)
        return 0;

    PrintShoppingList(traverse->lc);
    printf("< %d , %d >,",traverse->pid,traverse->qty);
    PrintShoppingList(traverse->rc);
    return 1;
}


struct Shop * NewShop(int sid, int type)
{
   struct Shop * newShop;
   newShop =(struct Shop *)malloc(sizeof(struct Shop));
   newShop->sid = sid;
   newShop->type = type;
   newShop->lc = &shopSentinel;
   newShop->rc = &shopSentinel;
   newShop->products = NULL;
   return newShop;
}

struct Shop * InsertShop(struct Shop * tempShop,int sid , int type)
{

    if (tempShop == &shopSentinel) return NewShop(sid, type);
    if(tempShop->sid >sid) tempShop->lc = InsertShop(tempShop->lc,sid,type);
    if(tempShop->sid <sid) tempShop->rc = InsertShop(tempShop->rc,sid,type);
    return tempShop;
}

/**
 * @brief Register shop 
 *
 * @param sid   The shop's id
 * @param type  The shop's type
 *
 * @return 1 on success
 *         0 on failure
 */
int RegisterShop (int sid, int type)
{
    struct Shop * tempShop ;
    tempShop = shopRoot;
    if(shopRoot == &shopSentinel)  shopRoot = NewShop(sid,type);
    else                           InsertShop(tempShop, sid, type);
    PrintR(sid,type);

	return 1;
}

struct Shop * FindShop(struct Shop * traverseShop,int sid)
{

    if(traverseShop->sid == sid) return traverseShop;
    if(traverseShop->sid > sid) return FindShop(traverseShop->lc,sid);
    if(traverseShop->sid < sid) return FindShop(traverseShop->rc,sid);
    return traverseShop;

}

/**
 * @brief Supply shop 
 *
 * @param sid       The shop's id
 * @param pid       The product's id
 * @param quantity  The product's quantity
 * @param price     The product's price
 *
 * @return 1 on success
 *         0 on failure
 */
int SupplyShop (int sid, int pid, int quantity, int price)
{
    struct Shop * targetShop;
    struct Shop * traverseShop;
    struct Product * newProd, * tempProd;
    shopSentinel.sid = sid;
    traverseShop = shopRoot;
    targetShop = FindShop(traverseShop,sid);
    if(targetShop == &shopSentinel) return 0;

    /*creation of product*/
    newProd = (struct Product*)malloc(sizeof(struct Product));
    newProd->qty = quantity;
    newProd->pid = pid;
    newProd->price = price;

    if(targetShop->products==NULL || targetShop->products->pid>=pid)
    {
        newProd->next = targetShop->products;
        targetShop->products = newProd;
    }
    else
    {
        tempProd = targetShop->products;
        while( tempProd->next && tempProd->next->pid <=pid )
            tempProd = tempProd->next;
        if(tempProd->pid == pid )
        {
            tempProd->qty += quantity;
            return 1;
        }
        newProd->next = tempProd->next;
        tempProd->next = newProd;
    }

    PrintS(sid,pid,quantity,price);
	return 1;
}


int FindSize(int length)
{
    int i;
    for( i=0 ; i< 368 ; i++)
        if(primes_g[i]>= length)
            break;
    return i;
}







int AllocateHash()
{
  int length;
  int prime;
  int i;
  /*go to the prime number after max_customers_g that is almost 30% bigger than it*/
  length = max_customers_g + max_customers_g*0.3;
  prime = FindSize(length);
  if(prime == -1) return 0;
  length = primes_g[prime];
  hash_size = prime;
  customers_hash = (struct Customer**)malloc(sizeof(struct Customer *)*length);

  for(i=0; i<length ; i++)
      customers_hash[i] = NULL;


  return 1;
}

struct Customer * NewCustomer(int cid)
{
   struct Customer * newCustomer;
   newCustomer = (struct Customer*)malloc(sizeof(struct Customer));
   newCustomer->cid = cid;
   newCustomer->shopping_tree = NULL;
   newCustomer->shopping_size = 0;
   return newCustomer;
}

int FirstHashFunction(int cid)
{
	return cid%primes_g[hash_size];
}
int SecondHashFunction(int cid)
{
	return primes_g[hash_size-1]- cid%primes_g[hash_size-1];

}

int Hashing(int cid)
{
  int result;
  result = FirstHashFunction(cid);
  if(customers_hash[result]!=NULL)
  {
      printf("\nCOLLISION HAPPENED with %d!\n",customers_hash[result]->cid);
      return DoubleHashing(cid, result);
  }
  else
  	customers_hash[result] = NewCustomer(cid);
  return 1;
}





int DoubleHashing(int cid, int hash_1)
{
  int i;
  int hash_2;
  int result;
  i = 0;
  hash_2 = SecondHashFunction(cid);

  while(1)
  {
	  result = (hash_1 + i * hash_2) %
					 primes_g[hash_size];

	  if (customers_hash[result] == NULL)
	  {
		  customers_hash[result] = NewCustomer(cid);
		  break;
	  }
	  i++;
  }
  return 1;


}
/**
 * @brief Register customer
 *
 * @param cid The customer's id
 *
 * @return 1 on success
 *         0 on failure
 */

int RegisterCustomer (int cid)
{
	if(max_customers_g == 0) return 0;
	/*each time a new customer is added, decrement max_customers_g*/

    if(customers_hash == NULL)
        AllocateHash();
    Hashing(cid);

    max_customers_g--;
    PrintC(cid);
	return 1;
}


struct Customer * FindCustomer(int cid)
{
  int hash_1;
  int i,result;
  hash_1 = FirstHashFunction(cid);
  if(customers_hash[hash_1] == NULL ) return NULL;
  if(customers_hash[hash_1]->cid == cid) return customers_hash[hash_1];

  else
  {
    i=0;
      while(1)
      {
          result = (hash_1+ i * SecondHashFunction(cid)) %primes_g[hash_size];

          if (customers_hash[result] == NULL)
             break;
          else
          {
              if(customers_hash[result]->cid == cid)
                  return customers_hash[result];
          }
          i++;
      }

  }
  return NULL;
}

struct C_Product * NewProduct(int pid, int quantity)
{
      struct C_Product * newProduct;
      newProduct = (struct C_Product *)malloc(sizeof(struct C_Product));
      newProduct->pid = pid;
      newProduct->qty = quantity;
      newProduct->lc = NULL;
      newProduct->rc = NULL;
      return newProduct;
}


struct C_Product * InsertShoppingTree(struct Customer * targetCustomer,struct C_Product * productList,int pid,int quantity)
{

    if(productList == NULL)
    {
        targetCustomer->shopping_size++;
        return NewProduct(pid,quantity);
    }


    if(productList->pid > pid)
        productList->lc = InsertShoppingTree(targetCustomer,productList->lc,pid, quantity);
    else if(productList->pid < pid)
        productList->rc = InsertShoppingTree(targetCustomer,productList->rc,pid, quantity);
    else
        productList->qty += quantity;

    return productList;


}
/**
 * @brief Add to shopping list
 *
 * @param cid       The customer's id
 * @param pid       The product's id
 * @param quantity  The quantity of the product
 *
 * @return 1 on success
 *         0 on failure
 */

int AddToShoppingList (int cid, int pid, int quantity)
{
    struct Customer* targetCustomer;
    struct C_Product * traverseList;

    targetCustomer = FindCustomer(cid);
    if(targetCustomer == NULL) return 0;
    traverseList = targetCustomer->shopping_tree;
    if(traverseList == NULL)    targetCustomer->shopping_tree = InsertShoppingTree(targetCustomer,traverseList,pid,quantity);
    else                        InsertShoppingTree(targetCustomer,traverseList,pid,quantity);
    PrintL(cid,pid,quantity);

	return 1;
}

struct C_Product * FindMinimum(struct C_Product * product)
{
    struct C_Product * traverseProduct;

    traverseProduct = product;

    while (traverseProduct!=NULL && traverseProduct->lc!= NULL)
        traverseProduct = traverseProduct->lc;

    return traverseProduct;

}


struct C_Product * DeleteShoppingTree(struct C_Product * product,int pid)
{
  /* case 1 : the node is a leaf
   * case 3 : node has only one child
   * case 4 : node has more than one child
   */
   struct C_Product * tempProduct;


   if(product == NULL) return product;
   else if(product->pid < pid ) product->rc = DeleteShoppingTree(product->rc,pid);
   else if(product->pid > pid)  product->lc = DeleteShoppingTree(product->lc,pid);

   else
   {
        if (product->lc == NULL)
        {
            tempProduct = product->rc;
            free(product);
            return tempProduct;
        }
        else if(product->rc == NULL)
        {
             tempProduct = product->lc;
             free(product);
             return tempProduct;
        }
        else
        {
            /*Find the smaller by traversing to left(the right subtree)*/
            tempProduct = FindMinimum(product->rc);
            product->pid = tempProduct->pid;
            product->qty = tempProduct->qty;

            product->rc = DeleteShoppingTree(product->rc, tempProduct->pid);

        }

    }

    return product;
}

/**
 * @brief Delete from shopping list
 *
 * @param cid  The customer's id
 * @param pid  The product's id
 * 
 * @return 1 on success
 *         0 on failure
 */
int DeleteFromShoppingList (int cid, int pid)
{
    struct Customer * targetCustomer;
    struct C_Product * deleteProduct;
    targetCustomer = FindCustomer(cid);
    if(targetCustomer == NULL) return 0;
    deleteProduct = targetCustomer->shopping_tree;
    targetCustomer->shopping_tree = DeleteShoppingTree(deleteProduct,pid);
    targetCustomer->shopping_size--;
    PrintD(cid,pid);
	return 1;
}



struct C_Product ** FillProductList(struct C_Product ** productList ,struct C_Product * product_tree)
{

    if(product_tree == NULL) return productList;
    productList = FillProductList(productList,product_tree->lc);
    productList[iterator++] = product_tree;
    productList = FillProductList(productList,product_tree->rc);
    return productList;
}

struct C_Product ** CreateProductList(struct Customer * targetCustomer)
{
    struct C_Product ** list;
    int size;
    int i;

    size = targetCustomer->shopping_size;
    list =(struct C_Product **)malloc(sizeof(struct C_Product *)*size);
    for(i=0;i<size;i++)
        list[i] = NULL;
    return list;

}

struct Shop * FindMinimumShop(struct Shop * shop)
{
    struct Shop * traverseShop;

    traverseShop = shop;

    while (traverseShop!=NULL && traverseShop->lc!= NULL)
        traverseShop = traverseShop->lc;

    return traverseShop;

}


struct Shop * CloseShop(struct Shop * traverseShop,int sid)
{
    /* case 1 : the node is a leaf
     * case 3 : node has only one child
     * case 4 : node has more than one child
     */
    struct Shop * temptraverseShop;

    if(traverseShop == &shopSentinel) return traverseShop;
    else if(traverseShop->sid < sid ) traverseShop->rc = CloseShop(traverseShop->rc,sid);
    else if(traverseShop->sid > sid)  traverseShop->lc = CloseShop(traverseShop->lc,sid);

    else
    {
        if (traverseShop->lc == &shopSentinel)
        {
            temptraverseShop = traverseShop->rc;
            free(traverseShop);
            return temptraverseShop;
        }
        else if(traverseShop->rc == &shopSentinel)
        {
            temptraverseShop = traverseShop->lc;
            free(traverseShop);
            return temptraverseShop;
        }
        else
        {
            /*Find the smaller by traversing to left(the right subtree)*/
            temptraverseShop = FindMinimumShop(traverseShop->rc);
            traverseShop->sid = temptraverseShop->sid;
            traverseShop->type = temptraverseShop->type;
            traverseShop->products = temptraverseShop->products;

            traverseShop->rc = CloseShop(traverseShop->rc, temptraverseShop->sid);

        }
    }

    return traverseShop;
}


int DeleteShop (int sid)
{
    struct Shop * traverseShop;
    traverseShop = shopRoot;

    shopRoot = CloseShop(traverseShop, sid);
    return 1;

}

int  VisitShop(struct C_Product *** product_,struct Shop * traverse_,int size)
{
    struct Product * shopProduct ;
    struct Product * prevProduct;
    int i;

    i=0;
    shopProduct = traverse_->products;
    prevProduct = NULL;

    while(i<size && shopProduct!=NULL)
    {
        while(shopProduct && (*(*product_+i))->pid > shopProduct->pid)
        {
            prevProduct = shopProduct;
            shopProduct = shopProduct->next;
        }

        if(shopProduct!=NULL && shopProduct->pid == (*(*product_+i))->pid)
        {


            if (shopProduct->qty == (*(*product_+i))->pid)
            {
                shopProduct->qty = 0;
                (*(*product_+i))->qty = 0;
                iterator--;
            }
            if (shopProduct->qty < (*(*product_+i))->qty)
            {
                (*(*product_+i))->qty -= shopProduct->qty;
                shopProduct->qty = 0;
            }
            if (shopProduct->qty > (*(*product_+i))->qty)
            {
                shopProduct->qty -= (*(*product_+i))->qty;
                (*(*product_+i))->qty = 0;
                iterator--;
            }

            /*--------Deletion-----------*/
            if (shopProduct->qty == 0)
            {
                if(prevProduct == NULL)
                {
                    traverse_->products = shopProduct->next;
                    free(shopProduct);
                    shopProduct = traverse_->products;

                }
                else
                {
                    prevProduct->next = shopProduct->next;
                    free(shopProduct);
                    shopProduct = prevProduct->next;
                }
            }
            /*-------------------------*/


        }

        i++;
    }

    /*-------Deletion----------*/
    if(traverse_->products == NULL)
        DeleteShop( traverse_->sid);

    return iterator;
}

int StartShopping(struct C_Product *** product,struct Shop * traverse,int size)
{

  if(traverse == &shopSentinel) return iterator;
  StartShopping(product,traverse->lc,size);
  iterator = VisitShop(product,traverse,size);
  StartShopping(product,traverse->rc,size);
  return iterator;
}

int DeleteWholeTree(struct C_Product* productList)
{

    if (productList == NULL) return 0;
    DeleteWholeTree(productList->lc);
    DeleteWholeTree(productList->rc);

    free(productList);
    return 1;
}


/**
 * @brief Go shopping
 *
 * @param cid The customer's id
 *
 * @return 1 on success
 *         0 on failure
 */
int GoShopping (int cid)
{
    struct Customer * targetCustomer;
    struct C_Product ** productList;
    struct C_Product * product_tree;
    struct Shop * traverseShop;
    int i;

    iterator = 0;
    traverseShop = shopRoot;
    targetCustomer = FindCustomer(cid);
    if( targetCustomer == NULL ) return 0;
    productList = CreateProductList(targetCustomer);
    product_tree = targetCustomer->shopping_tree;
    productList = FillProductList(productList,product_tree);
    iterator = targetCustomer->shopping_size;
    iterator = StartShopping(&productList,traverseShop,targetCustomer->shopping_size);

    if(iterator == 0)
    {
        DeleteWholeTree(product_tree);
        targetCustomer->shopping_size = 0;
    }
    else
    {
        for ( i=0 ; i<targetCustomer->shopping_size;i++)
            if((*(productList+i))->qty == 0 )
            {
                targetCustomer->shopping_tree = DeleteShoppingTree(targetCustomer->shopping_tree,(*(productList+i))->pid);
                targetCustomer->shopping_size--;
            }

    }

    PrintG(cid,targetCustomer);
    return 1;
}

struct C_Product * CreateBalancedTree(struct C_Product ** product, int start,int end)
{
    struct C_Product * newTree;

    if (start > end)   return NULL;


    int middle = (start + end)/2;
    newTree =(struct C_Product *)malloc(sizeof(struct C_Product));
    newTree->pid = product[middle]->pid;
    newTree->qty = product[middle]->qty;



    newTree->lc = CreateBalancedTree(product, start,middle - 1);

    newTree->rc = CreateBalancedTree(product, middle + 1, end);

    return newTree;

}

int DeleteCustomer (struct Customer * customer)
{
  int hash_1;
  int i,result;
  hash_1 = FirstHashFunction(customer->cid);
  if(customers_hash[hash_1]->cid == customer->cid) customers_hash[hash_1] = NULL;
  else
  {
      i=0;
      while(1)
      {
            result = (hash_1+ i * SecondHashFunction(customer->cid)) %primes_g[hash_size];

            if(customers_hash[result]->cid == customer->cid)
            {
                customers_hash[result] = NULL;
                break;
            }
            i++;
        }

    }
    free(customer);
    return 1;
}

/**
 * @brief Customers shop together
 * 
 * @param cid1  The id of the first customer
 * @param cid2  The id of the second customer
 *
 * @return 1 on success
 *         0 on failure
 */
int ShopTogether (int cid1, int cid2)
{
    struct Customer * firstCustomer, * secondCustomer ;
    struct C_Product ** productList1, ** productList2, ** productFinal , *tempProduct;
    int i,j,m;
    int size_1,size_2,size_3;
    int quantity;
    i=0;
    j=0;
    m=0;

    firstCustomer = FindCustomer(cid1);
    secondCustomer = FindCustomer(cid2);
    if(secondCustomer==NULL || firstCustomer==NULL) return 0;

    /* first array */
    iterator = 0;

    productList1 = CreateProductList(firstCustomer);
    productList1 = FillProductList(productList1,firstCustomer->shopping_tree);
    size_1 = firstCustomer->shopping_size;

    /* second array */
    iterator = 0;

    productList2 = CreateProductList(secondCustomer);
    productList2 = FillProductList(productList2,secondCustomer->shopping_tree);
    size_2 = secondCustomer->shopping_size;

    /* third array */
    firstCustomer->shopping_size += secondCustomer->shopping_size;
    productFinal = CreateProductList(firstCustomer);
    size_3 = firstCustomer->shopping_size;

    /* merge arrays */

    while (i<size_1 && j <size_2)
    {
        if (productList1[i]->pid < productList2[j]->pid) productFinal[m] = productList1[i++];

        else if(productList1[i]->pid == productList2[j]->pid)
        {
            quantity = productList1[i]->qty + productList2[j]->qty;
            productList1[i]->qty = quantity;
            productFinal[m] = productList1[i];
            i++;
            j++;
            size_3 --;

        }
        else                   productFinal[m] = productList2[j++];
        m++;
    }
    while (i < size_1)        productFinal[m++] = productList1[i++];
    while (j < size_2)        productFinal[m++] = productList2[j++];

    /*-------------*/

   tempProduct = firstCustomer->shopping_tree;
   firstCustomer->shopping_size = size_3;
   firstCustomer->shopping_tree = CreateBalancedTree(productFinal,0,size_3-1);

   DeleteWholeTree(tempProduct);
   DeleteWholeTree(secondCustomer->shopping_tree);
   DeleteCustomer(secondCustomer);

   PrintM(cid1,cid2,firstCustomer);
   return 1;
}

/**
 * @brief Categorize shops
 *
 * @return 1 on success
 *         0 on failure
 */
int CategorizeShops (void)
{
    /*...............................*/
	return 1;
}

/**
 * @brief Print shops
 *
 * @return 1 on success
 *         0 on failure
 */
int PrintShops (void)
{
    struct Shop * traverseShop;
    printf("Event X:\n");
    traverseShop = shopRoot;
    PrintShoppingTree(traverseShop);
    printf("\nDONE\n");
	return 1;
}

/**
 * @brief Print customers
 *
 * @return 1 on success
 *         0 on failure
 */
int PrintCustomers (void)
{
    int i;
    printf("Event Y:\n");

    for(i=0 ; i< primes_g[hash_size];i++)
    {

        if(customers_hash[i]!=NULL)
        {
            printf("   Customer %d =",customers_hash[i]->cid);
            PrintShoppingList(customers_hash[i]->shopping_tree);
            printf("\n");
        }
    }
    printf("\nDONE\n");
	return 1;
}





/**
 * @brief Free resources
 *
 * @return 1 on success
 *         0 on failure
 */
int FreeAll(void)
{
	return 1;
}


/**
 * @brief The main function
 *
 * @param argc Number of arguments
 * @param argv Argument vector
 *
 * @return 0 on success
 *         1 on failure
 */
int main(int argc, char** argv)
{
	FILE *fin = NULL;
	char buff[BUFFER_SIZE], event;

	/* Check command buff arguments */
	if (argc != 2) {
		fprintf(stderr, "Usage: %s <input_file> \n", argv[0]);
		return EXIT_FAILURE;
	}

	/* Open input file */
	if (( fin = fopen(argv[1], "r") ) == NULL ) {
		fprintf(stderr, "\n Could not open file: %s\n", argv[1]);
		perror("Opening test file\n");
		return EXIT_FAILURE;
	}

	/* Initializations */
	Initialize();

	/* Read input file buff-by-buff and handle the events */
	while (fgets(buff, BUFFER_SIZE, fin)) {

		DPRINT("\n>>> Event: %s", buff);

		switch(buff[0]) {

			/* Comment */
			case '#':
				break;

			/* dummy event '0'. Get the total number of customers */
			case '0':
				{
					sscanf(buff, "%c %u", &event, &max_customers_g);
					DPRINT("%c MAX CUSTOMERS: %u\n", event, max_customers_g);
					break;
				}

				/* Register shop
				 * R <sid> <type> */
			case 'R':
				{
					int sid, type;

					sscanf(buff, "%c %d %d", &event, &sid, &type);
					DPRINT("%c %d %d\n", event, sid, type);

					if (RegisterShop(sid, type)) {
						DPRINT("%c %d %d succeeded\n", event, sid, type);
					} else {
						fprintf(stderr, "%c %d %d failed\n", event, sid, type);
					}

					break;
				}

				/* Supply shop
				 * S <sid> <pid> <quantity> <price> */
			case 'S':
				{
					int sid, pid, quantity, price;

					sscanf(buff, "%c %d %d %d %d", &event, &sid, &pid, &quantity, &price);
					DPRINT("%c %d %d %d %d\n", event, sid, pid, quantity, price);

					if (SupplyShop(sid, pid, quantity, price)) {
						DPRINT("%c %d %d %d %d succeeded\n", event, sid, pid, quantity, price);
					} else {
						fprintf(stderr, "%c %d %d %d %d failed\n", event, sid, pid, quantity, price);
					}

					break;
				}

				/* Register customer
				 * C <cid> */
			case 'C':
				{
					int cid;

					sscanf(buff, "%c %d", &event, &cid);
					DPRINT("%c %d\n", event, cid);

					if (RegisterCustomer(cid)) {
						DPRINT("%c %d succeeded\n", event, cid);
					} else {
						fprintf(stderr, "%c %d failed\n", event, cid);
					}

					break;
				}

				/* Add to shopping list
				 * L <cid> <pid> <quantity> */
			case 'L':
				{
					int cid, pid, quantity;

					sscanf(buff, "%c %d %d %d", &event, &cid, &pid, &quantity);
					DPRINT("%c %d %d %d\n", event, cid, pid, quantity);

					if (AddToShoppingList(cid, pid, quantity)) {
						DPRINT("%c %d %d %d succeeded\n", event, cid, pid, quantity);
					} else {
						fprintf(stderr, "%c %d %d %d failed\n", event, cid, pid, quantity);
					}

					break;
				}

				/* Delete from shopping list
				 * D <cid> <pid> */
			case 'D':
				{
					int cid, pid;

					sscanf(buff, "%c %d %d", &event, &cid, &pid);
					DPRINT("%c %d %d\n", event, cid, pid);

					if (DeleteFromShoppingList(cid, pid)) {
						DPRINT("%c %d %d succeeded\n", event, cid, pid);
					} else {
						fprintf(stderr, "%c %d %d failed\n", event, cid, pid);
					}

					break;
				}

				/* Go shopping
				 * G <cid> */
			case 'G':
				{
					int cid;

					sscanf(buff, "%c %d", &event, &cid);
					DPRINT("%c %d\n", event, cid);

					if (GoShopping(cid)) {
						DPRINT("%c %d succeeded\n", event, cid);
					} else {
						fprintf(stderr, "%c %d failed\n", event, cid);
					}

					break;
				}

				/* Shop together
				 * M <cid1> <cid2> */
			case 'M':
				{

					int cid1, cid2;

					sscanf(buff, "%c %d %d", &event, &cid1, &cid2);
					DPRINT("%c %d %d\n", event, cid1, cid2);

					if (ShopTogether(cid1, cid2)) {
						DPRINT("%c %d %d succeeded\n", event, cid1, cid2);
					} else {
						fprintf(stderr, "%c %d %d failed\n", event, cid1, cid2);
					}

					break;
				}

				/* Categorize shops
				 * T */
			case 'T':
				{
					sscanf(buff, "%c", &event);
					DPRINT("%c\n", event);

					if (CategorizeShops()) {
						DPRINT("%c succeeded\n", event);
					} else {
						fprintf(stderr, "%c failed\n", event);
					}

					break;
				}

				/* Print shops
				 * X */
			case 'X':
				{
					sscanf(buff, "%c", &event);
					DPRINT("%c\n", event);

					if (PrintShops()) {
						DPRINT("%c succeeded\n", event);
					} else {
						fprintf(stderr, "%c failed\n", event);
					}

					break;
				}

				/* Print customers
				 * Y */
			case 'Y':
				{
					sscanf(buff, "%c", &event);
					DPRINT("%c\n", event);

					if (PrintCustomers()) {
						DPRINT("%c succeeded\n", event);
					} else {
						fprintf(stderr, "%c failed\n", event);
					}

					break;
				}

				/* Empty line */
			case '\n':
				break;

				/* Ignore everything else */
			default:
				DPRINT("Ignoring buff: %s \n", buff);

				break;
		}
	}

	FreeAll();
	return (EXIT_SUCCESS);
}
