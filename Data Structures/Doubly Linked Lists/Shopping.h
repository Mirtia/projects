 /*************************************************************
 * @file   Shopping.h                                        *
 * @author Nikolaos Batsaras <batsaras@csd.uoc.gr>  	     *
 *                                                    	     *
 * @brief  Header file for the needs of CS-240b project 2018 *
 ************************************************************/

#ifndef __SHOPPING_H__
#define __SHOPPING_H__



struct Shop {
	int sid;						/*id of Shop (unique)*/
	int type;						/*type of Shop*/
	struct Product *products;		/*pointer to List of Products (ordered)*/
	struct Shop *next;				/*pointer to next Shop*/
	struct Shop *prev;				/*pointer to prev Shop*/
};

struct Customer {
	int cid;						    /*id of Customer (unique)*/
	struct C_Product *shoppping_list;   /*pointer to List of C_Products (ordered)*/
	struct Customer *next;			    /*pointer to next Customer*/
};

struct Product {
	int pid;						/* id of Product (unique)*/
	int quantity;					/* current quantity available at shop */
	int price;						/*price of Product*/
	struct Product *next;			/*pointer to next Product*/
};

struct C_Product {
	int pid;						/* id of C_Product (unique)*/
	int quantity;					/*quantity of the product the Customer wants to buy*/
	struct C_Product *next;			/* pointer to next C_Product*/
};


/* Global, pointer to the header node of the circular, double-linked list of shops */
struct Shop *H;

/* Global, pointer to the head of the list of customers */
struct Customer *Customers;

/* Global, pointer to the sentinel node of the list of customers */
struct Customer *Sentinel;

/* Global, array of pointers for the use in event 'Categorize Stores' */
struct Shop *ShopTypes[5];

/* Global, array of pointers for the use in event 'Find Cheapest Products' */
struct Product *CheapestProducts[5];


#endif /* __SHOPPING_H__ */
