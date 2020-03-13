/*************************************************************
 * @file   main.c                                            *
 * @author Nikolaos Batsaras <batsaras@csd.uoc.gr>  	     *
 *                                                    	     *
 * @brief  Source file for the needs of CS-240b project 2018 *
 ************************************************************/

#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <assert.h>

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




/*Functions for PRINT events*/


int printR(int sid , int type)
{
    struct Shop * tempShop;
    tempShop = H->next;
    printf("R:\n");
    printf("Shop sid:%d . type: %d\n\n", sid ,type);
    printf("Shops:");
    while(tempShop!=H)
    {
        printf("sid: %d .",tempShop->sid);
        tempShop = tempShop->next;
    }
    printf(" \nDONE\n");
    return 1;
}


int printC(int cid)
{
    struct  Customer * tempCustomer;
    printf("C:\n");
    printf("Customer cid: %d \n\n",cid);
    tempCustomer = Customers;
    while(tempCustomer!=Sentinel)
    {
        printf("cid: %d .",tempCustomer->cid);
        tempCustomer = tempCustomer->next;
    }
    printf(" \nDONE\n");
    return 1;
}

int printT(void)
{
    int i;
    struct Shop * tempShop;
    struct Product * tempProd;
    printf("T:\n");
    for(i=0;i<5;i++)
    {
        printf("Shop_Category %d:\n",i);
        tempShop = ShopTypes[i];

        while(tempShop!=NULL)
        {
            tempProd = tempShop->products;
            while (tempProd)
            {
                printf("| pid: %d quantity: %d price: %d .", tempProd->pid, tempProd->quantity, tempProd->price);
                tempProd = tempProd->next;
            }
            tempShop = tempShop->next;
        }
        printf("\n");

    }
    printf(" \nDONE\n");
    return 1;
}

int  printF(void)
{
    int i;
    printf("F:\n");
    for( i=0;i<5;i++)
    {
        printf("Product %d: %d \n",CheapestProducts[i]->pid,CheapestProducts[i]->price);
    }
    printf(" \nDONE\n");
    return 1;
}

int printM(int sid_1 , int sid_2)
{
    struct Shop * tempShop;
    struct Product * tempProd;
    tempShop = H->next;
    printf("M:\n");
    printf("Shop_1 (acquisitor): %d  Shop_2 (closing) %d\n\n",sid_1, sid_2);

    while(tempShop!=H)
    {
        printf("Shop %d . type : %d : ",tempShop->sid , tempShop->type);
        tempProd = tempShop->products;
        while(tempProd)
        {
            printf("| pid: %d ,quantity %d, price: %d .",tempProd->pid,tempProd->quantity,tempProd->price);
            tempProd = tempProd->next;
        }
        printf("\n");
        tempShop =tempShop->next;
    }
    printf(" \nDONE\n");
    return 1;
}


int printS(int sid,int pid,int quantity, int price)
{
    struct Shop * tempShop;
    struct Product * tempProd;
    printf("S:\n");
    printf("Shop sid: %d | Product pid: %d  quantity: %d price: %d \n\n", sid ,pid ,quantity , price );
    /*PrintShops*/

    tempShop = H->next;
    while(tempShop!=H)
    {
        printf("Shop %d : ",tempShop->sid );
        tempProd = tempShop->products;
        while(tempProd)
        {
            printf("| pid: %d ,quantity %d, price: %d .",tempProd->pid,tempProd->quantity,tempProd->price);
            tempProd = tempProd->next;
        }
        printf("\n");
        tempShop =tempShop->next;
    }
    printf(" \nDONE\n");
    return 1;
}

int printD(int cid , int pid)
{
    struct Customer * tempCustomer;
    struct C_Product * tempProd;
    tempCustomer = Customers;
    printf("D:\n");
    printf("Customer cid: %d | Product pid: %d\n\n",cid,pid);

    while(tempCustomer!=Sentinel)
    {
        printf("Customer %d = ", tempCustomer->cid);
        tempProd = tempCustomer->shoppping_list;
        while (tempProd) {
            printf("| pid = %d . quantity = %d .", tempProd->pid, tempProd->quantity);
            tempProd = tempProd->next;
        }
        printf("\n");
        tempCustomer = tempCustomer->next;
    }

    printf(" \nDONE\n");
    return 1;
}

int printG(int cid)
{
    struct Shop * tempShop;
    struct Product * tempProd;
    struct C_Product * tempCrod;
    struct Customer * tempCustomer;
    tempShop = H->next;
    tempCustomer = Customers;

    printf("G:\n");
    printf("Customer %d =  \n\n", cid);




    while(tempCustomer!=Sentinel)
    {
        printf("Customer %d = ", tempCustomer->cid);
        tempCrod = tempCustomer->shoppping_list;
        while (tempCrod) {
            printf("| pid = %d . quantity = %d .", tempCrod->pid, tempCrod->quantity);
            tempCrod = tempCrod->next;
        }
        printf("\n");
        tempCustomer = tempCustomer->next;
    }

    printf("\n");

    while(tempShop!=H)
    {
        printf("Shop %d . type : %d : ",tempShop->sid , tempShop->type);
        tempProd = tempShop->products;
        while(tempProd)
        {
            printf("| pid: %d ,quantity %d, price: %d .",tempProd->pid,tempProd->quantity,tempProd->price);
            tempProd = tempProd->next;
        }
        printf("\n");
        tempShop =tempShop->next;
    }
    printf(" \nDONE\n");
    return 1;
}


int printL(int cid , int pid , int quantity)
{


    struct Customer * tempCustomer;
    struct C_Product * tempProd;
    tempCustomer = Customers;
    printf("L:\n");
    printf("Customer cid: %d | Product pid: %d . quantity: %d \n\n",cid,pid,quantity);

    while(tempCustomer!=Sentinel)
    {
        printf("Customer %d = ", tempCustomer->cid);
        tempProd = tempCustomer->shoppping_list;
        while (tempProd) {
            printf("| pid = %d . quantity = %d .", tempProd->pid, tempProd->quantity);
            tempProd = tempProd->next;
        }
        printf("\n");
        tempCustomer = tempCustomer->next;
    }
    printf(" \nDONE\n");
    return 1;
}

/*-----------------------*/


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
	struct Shop * Header;
	Header= (struct Shop*)malloc(sizeof(struct Shop));
	Header->prev =Header;
	Header->next =Header;
	Header->sid = -1;
	Header->type =-1;


	H = Header;
	/*Customers = (struct Customer*)malloc(sizeof(struct Customer));*/
	Sentinel = (struct Customer*)malloc(sizeof(struct Customer));
	Customers = Sentinel; /*Customer headPtr points at the Sentinel as no other insertion has happened*/
	for(i=0;i<5;i++)
	{
		ShopTypes[i] = NULL;
		CheapestProducts[i] = NULL;

	}


	return 1;
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
    struct Shop * newShop;
	if(H==NULL)return 0;

	newShop = (struct Shop*)malloc(sizeof(struct Shop));
	newShop->type = type;
	newShop->sid = sid;
    newShop ->prev = H;
    if(H->prev == H)
    {
        newShop->next = H;
        H->prev = newShop;
    }
    else
        newShop->next = H->next;

    H->next = newShop;

    return 1;
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
    struct Shop * headPtr ;
    struct Product * newProd, * tempPtr;

    headPtr = H->next;
    while(headPtr!=H)
    {
       if(headPtr->sid == sid)
       {
           /*"creation" of product*/
            newProd = (struct Product*)malloc(sizeof(struct Product));
            newProd->quantity = quantity;
            newProd->pid = pid;
            newProd->price = price;

            if(headPtr->products==NULL || headPtr->products->pid>=pid)
            {
                newProd->next = headPtr->products;
                headPtr->products = newProd;
            }
            else
            {
                tempPtr = headPtr->products;
                while( tempPtr->next && tempPtr->next->pid <=pid )
                    tempPtr = tempPtr->next;
                if(tempPtr->pid == pid )
                {
                    tempPtr->quantity += quantity;
                    return 1;
                }
                newProd->next = tempPtr->next;
                tempPtr->next = newProd;
            }
            return 1;
       }
       headPtr = headPtr->next;

    }


	return 0;
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
	struct Customer * newCustomer;
    newCustomer = (struct Customer*)malloc(sizeof(struct Customer));
	newCustomer->cid = cid;

	newCustomer->next = Customers;
	Customers = newCustomer;


    return 1;
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
  	struct C_Product * newProd, * tempProd;
	struct Customer * headCustomer;
	headCustomer = Customers;
	Sentinel->cid = cid;
	while( headCustomer->cid!=cid ) headCustomer = headCustomer->next;
	if(headCustomer == Sentinel) return 0;

    newProd = (struct C_Product*)malloc(sizeof(struct C_Product));
    newProd->pid = pid;
    newProd->quantity = quantity;

	if(!headCustomer->shoppping_list || headCustomer->shoppping_list->pid >= pid)
    {
	    newProd->next = headCustomer->shoppping_list;
	    headCustomer->shoppping_list = newProd;

    }
	else
    {
	   tempProd = headCustomer->shoppping_list;
	   while(tempProd->next && tempProd->next->pid <= pid )
	         tempProd =tempProd->next;
	    if(tempProd->pid == pid )
	    {
            tempProd->quantity += quantity;
            return 1;
        }
        newProd->next = tempProd->next;
        tempProd->next = newProd;

    }


	return 1;
}

/*will use PrintCustomers*/

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

	struct Customer * headCustomer;
	struct C_Product * tempProd , * prevProd;
	headCustomer = Customers;
	Sentinel->cid = cid;
	while( headCustomer->cid!=cid ) headCustomer = headCustomer->next;
	if(headCustomer == Sentinel) return 0;
	tempProd = headCustomer->shoppping_list;
        prevProd = NULL;
	while(tempProd)
	{
	    if(tempProd->pid == pid)
	    {
            if (prevProd == NULL)
            {
                headCustomer->shoppping_list = tempProd->next;
                free(tempProd);

            }
            else
            {
                prevProd->next = tempProd->next;
                free(tempProd);

            }
            return 1;
        }
        prevProd = tempProd;
        tempProd = tempProd->next;
	}

	return 0;
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
    struct Customer * tempCustomer;
    struct C_Product * tempCrod ,* prevCrod ;
    struct Shop * tempShop;
    struct Product * tempProd, * prevProd ;

	tempShop = H->next;
    tempCustomer = Customers;
    Sentinel->cid = cid;
    while(tempCustomer->cid!=cid) tempCustomer = tempCustomer->next;
    if(tempCustomer == Sentinel)
    {
        printf("Customer not found\n");
        return 0; /*customer with cid not found*/
    }



    while(tempShop!=H )
    {
		prevCrod = NULL;
		tempCrod = tempCustomer->shoppping_list;

        while(tempCrod)
        {
			prevProd = NULL;
			tempProd = tempShop->products;
            while ( tempProd && tempCrod->pid >= tempProd->pid && tempCrod->quantity!=0)
            {
                if (tempCrod->pid == tempProd->pid)
                {
                    if (tempCrod->quantity == tempProd->quantity)
                    {
                        tempCrod->quantity = 0;
                        tempProd->quantity = 0;
                    }
                    if (tempCrod->quantity < tempProd->quantity)
                    {
                        tempProd->quantity -= tempCrod->quantity;
                        tempCrod->quantity = 0;
                    }
                    if (tempCrod->quantity > tempProd->quantity)
                    {
                        tempCrod->quantity -= tempProd->quantity;
                        tempProd->quantity = 0;
                    }
                    /*---------deletion----------*/
                    if (tempProd->quantity == 0)
                    {
                        if(prevProd == NULL)
                        {
                            tempShop->products = tempProd->next;
                            free(tempProd);
                            tempProd = tempShop->products;

                        }
                        else
                        {
                            prevProd->next = tempProd->next;
                            free(tempProd);
                            tempProd = prevProd->next;
                        }
                    }
                    /*-------------------------*/
                    else
                    {
                        prevProd = tempProd;
                        tempProd = tempProd->next;
                    }
                }
                else
				{
                	prevProd = tempProd;
                	tempProd = tempProd->next;
				}
            }

            /*--------deletion-------*/
            if(tempCrod->quantity == 0)
            {
                if(prevCrod == NULL)
                {
                    tempCustomer->shoppping_list = tempCrod->next;
                    free(tempCrod);
                    tempCrod = tempCustomer->shoppping_list;

                }
                else
                {
                    prevCrod->next = tempCrod->next;
                    free(tempCrod);
                    tempCrod = prevCrod->next;
                }

            }
            /*-------------------------*/
            else
            {
                prevCrod = tempCrod;
                tempCrod = tempCrod->next;
            }


        }
        tempShop = tempShop->next;
    }

    if(tempCustomer->shoppping_list == NULL) return 1;
    return 0;
}



/**
 * @brief Store close
 * 
 * @param sid1  The id of the acquisitor shop
 * @param sid2  The id of the closing shop
 *
 * @return 1 on success
 *         0 on failure  - case the Shops do not exist
 */
int StoreClose (int sid1, int sid2)
{
  struct Shop * tempShop_1, * tempShop_2 , *prevShop_2;
  struct Product * tempProd_1 , * tempProd_2 , * prevProd_1 ,* tempProd;
  tempShop_1 = H->next;
  tempShop_2 = H->next;
  prevShop_2 = H->next;

  while(tempShop_1!=H && tempShop_1->sid!=sid1)
  {
      tempShop_1 = tempShop_1->next;
  }
  while(tempShop_2!=H && tempShop_2->sid!=sid2)
  {
      prevShop_2 = tempShop_2;
      tempShop_2 = tempShop_2->next;

  }
  if( tempShop_2 == H || tempShop_1 == H ) return 0;

  tempProd_1 = tempShop_1->products;    /*acquisitor shop*/
  tempProd_2 = tempShop_2->products;    /*closing shop*/
  prevProd_1 = NULL;

  while( tempProd_2 && tempProd_1)
  {
      if(tempProd_2->pid < tempProd_1->pid)
      {
          if(prevProd_1==NULL)
          {
              tempProd = tempProd_2->next;
              tempProd_2->next = tempShop_1->products;
              tempShop_1->products = tempProd_2;

              tempProd_2 = tempProd;
              prevProd_1 = tempProd_1;
              tempProd_1 = tempProd_1->next;

          }
          else
          {
             prevProd_1->next = tempProd_2;
             tempProd = tempProd_2->next;
             tempProd_2->next = tempProd_1;
             tempProd_2 = tempProd;
             prevProd_1 = tempProd_1;
             tempProd_1 = tempProd_1->next;
          }
      }
      else if(tempProd_2->pid == tempProd_1->pid)
      {
          tempProd_1->quantity += tempProd_2->quantity;
          prevProd_1 = tempProd_1;
          tempProd_1 = tempProd_1->next;
      }
      else
      {
          prevProd_1 = tempProd_1;
          tempProd_1 =tempProd_1->next;
      }
  }
  if(tempProd_2 && prevProd_1) prevProd_1->next = tempProd_2;

  /*DELETE SHOP tempShop_2*/

  if(prevShop_2 == H->next)
  {
      H->next = tempShop_2->next;
      tempShop_2->next->prev = H;
      free(tempShop_2);
  }
  else
  {
      prevShop_2->next = tempShop_2->next;
      tempShop_2->next->prev = prevShop_2;
      free(tempShop_2);
  }


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
	struct Shop * tempShop, * headShop;
	tempShop = H->next;

	while(tempShop!=H)
	{
	  if(ShopTypes[tempShop->type] == NULL)
	  {
	      headShop = tempShop->next;
          tempShop->next = NULL;
          ShopTypes[tempShop->type] = tempShop;
          tempShop = headShop;
	  }
	  else
	  {
	      headShop = tempShop->next;
          tempShop->next = ShopTypes[tempShop->type];
          ShopTypes[tempShop->type] = tempShop;
          tempShop = headShop;

	  }

	}

    return 1;
}


/**
 * @brief Find cheapest products
 *
 * @return 1 on success
 *         0 on failure
 */
int FindCheapestProducts (void)
{
	int i;
	int j;
	struct Product * temp;
	struct Product * tempProd ;
	struct Shop * tempShop;
	tempShop = H->next;
	i=0;

	/*in the beginning we wills store first five products in array CheapestProducts*/
	while(tempShop)
	{
		tempProd = tempShop->products;
		while(tempProd)
		{
			if(i<5) CheapestProducts[i++] = tempProd;
			else break;
			tempProd = tempProd->next;
		}
		if(i==5) break;
		tempShop =tempShop->next;
	}
	tempProd = CheapestProducts[4];
	/*we will sort them */
	for(i=0;i<4;i++)
	{
		for(j=0;j<4-i;j++)
		{

			if(CheapestProducts[j]->price > CheapestProducts[j+1]->price)
			{
				temp = CheapestProducts[j+1];
				CheapestProducts[j+1] = CheapestProducts[j];
				CheapestProducts[j]= temp;
			}
		}
	}

	/*then we will continue traversing and comparing with current products*/
	while(tempShop!=H)
	{
		while(tempProd)
		{
			if( tempProd->price < CheapestProducts[4]->price )
			{
				for(i=0;i<5;i++)
				{
					if( CheapestProducts[i]->price > tempProd->price )    break;

				}
				for(j=4;j>i;j--)
				{
					CheapestProducts[j] = CheapestProducts[j-1];


				}
				CheapestProducts[j] = tempProd;
			}

			tempProd = tempProd->next;
		}
		tempShop = tempShop->next;
		tempProd = tempShop->products;

	}

	return 1;
}


/**
 * @brief Print shops
 *
 * @return 1 on success
 *         0 on failure      - failure ?
 */
int PrintShops (void)
{

    struct Shop * tempShop;
    struct Product * tempProd;
    tempShop = H->next;
    while(tempShop!=H)
    {
        printf("Shop %d . type : %d : ",tempShop->sid , tempShop->type);
        tempProd = tempShop->products;
        while(tempProd)
        {
            printf("| pid: %d ,quantity %d, price: %d .",tempProd->pid,tempProd->quantity,tempProd->price);
            tempProd = tempProd->next;
        }
        printf("\n");
        tempShop =tempShop->next;
    }


    return 1;
}

/**
 * @brief Print customers
 *
 * @return 1 on success
 *         0 on failure     - failure ?
 */
int PrintCustomers (void)
{

	struct Customer * tempCustomer;
	struct C_Product * tempProd;
	tempCustomer = Customers;

	while(tempCustomer!=Sentinel)
	{
        printf("Customer %d = ", tempCustomer->cid);
        tempProd = tempCustomer->shoppping_list;
        while (tempProd) {
            printf("| pid = %d . quantity = %d .", tempProd->pid, tempProd->quantity);
            tempProd = tempProd->next;
        }
        printf("\n");
        tempCustomer = tempCustomer->next;
    }

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
                    printR(sid,type);
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
                    printS(sid,pid,quantity,price);
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
                    printC(cid);
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
                    printL(cid,pid,quantity);
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
                    printD(cid,pid);
					break;
				}

				/* Go shopping
				 * G <cid> */
			case 'G':
				{
					PrintCustomers();
					PrintShops();
					int cid;

					sscanf(buff, "%c %d", &event, &cid);
					DPRINT("%c %d\n", event, cid);

					if (GoShopping(cid)) {
						DPRINT("%c %d succeeded\n", event, cid);
					} else {
						fprintf(stderr, "%c %d failed\n", event, cid);
					}
                    printG(cid);
					break;
				}


				/* Store close
				 * M <sid1> <sid2> */
			case 'M':
				{

					int sid1, sid2;

					sscanf(buff, "%c %d %d", &event, &sid1, &sid2);
					DPRINT("%c %d %d\n", event, sid1, sid2);

					if (StoreClose(sid1, sid2)) {
						DPRINT("%c %d %d succeeded\n", event, sid1, sid2);
					} else {
						fprintf(stderr, "%c %d %d failed\n", event, sid1, sid2);
					}
                    printM(sid1,sid2);
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
					printT();

					break;
				}

				/* Find cheapest products
				 * F */
			case 'F':
				{
					sscanf(buff, "%c", &event);
					DPRINT("%c\n", event);

					if (FindCheapestProducts()) {
						DPRINT("%c succeeded\n", event);
					} else {
						fprintf(stderr, "%c failed\n", event);
					}
                    printF();
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
