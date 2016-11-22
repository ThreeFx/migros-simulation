# Migros simulation

A collaboration of ETH students. (*we want cookies* 🍪)

How to collaborate:

1. Fork the repo (go to the repo and click on fork)

2. Work and commit (on your own repo)

3. Make a pull request

Structure suggestion:

- Main
- ICharImage or IDrawable
  - Person
    - Customer
    - Cashier
  - Checkout (with Conveyer, Counter, Cashier, Queue)
  - Item
  - Floor
- Image ("map")
- ...

Map:
P: Person head (unicode char, e.g. 👤) or just 🚶  
L: Person's lower part (unicode char, e.g. 👢,👘)  
C: Conveyer belt (unicode char)  
.: Floor tile (unicode char)  
S: Shopping cart: � ?  
I: Item (unicode, e.g. 🍪)  

    ......P.......PPPPPPP...|  
    ......L..CCCCCCCCIIIC...|  
    ....................P...|  
    .P.......CCCCCCCCCCCC...|  
    .L...............PPPP...|  
    .........CCCCCCCCCCCC...|  
    ......................P.|  
    .........CCCCCCCCCCCC.L.|  
    ____________________ ...| <-- exit

or scaled... maybe threepart person

Classes (old):

- Main application
- MigrosQueue (inherits from LinkedList)
- Cashier
- SelfCheckout (inherits from Customer)
- Customer
- Migros
- Ware (char)

Ideas:

- Interactive migros tour
