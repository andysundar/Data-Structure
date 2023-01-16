# LinkedList 
**Linked + List = A list which is made by linking several data element together.**

Linked List are the simplest form of dynamic data structures. 
Whenever new element is need to be inserted it simply create the object and link to the end of the list or wherever you 
want to add and whenever we want to remove an existing element we simply uncouple the link(s) from the list.
As it is not allocate consecutive memory allocation which results into easy insertion or deletion. 

## Node 
A node is a container of data & reference(s)/ link(s) to other node. Based on the linked list types **node** structure 
will change. 


### LinkedList Types
1. Single Linked List 
   - In this data structure node will contain the data and reference to next node and last node will point to NULL. 
   - We cannot go back to the previous node as we not have this information with us. 
   - As last node's next reference point to NULL. This act as an indicator for end of the list. 
```
   class SingleListNode<T> {                    /*  _________        _________              */
            private T data;                     /* |   data  |      |   data  |             */
                                                /* |_________|      |_________|             */
            private Node<T> nextNode;           /* |   next  |  --->|   next  |  ---> NULL  */
                                                /* |_________|      |_________|             */
    }
```

2. Double Linked List 
   - In this data structure node will contain the data and two references to refer next & previous node. 
   - As result of two references we can traverse back and forth in this list. 
   - First node's previous pointer will refer to NULL and last node's next pointer will refer to NULL. 
```
    class DoubleListNode<T> {  
                                                /*           _________        _________              */
            private Node<T> prevNode;           /*  NULL <--|   prev  |  <---|   prev  |             */
                                                /*          |_________|      |_________|             */
            private T data;                     /*          |   data  |      |   data  |             */
                                                /*          |_________|      |_________|             */
            private Node<T> nextNode;           /*          |   next  |  --->|   next  |  ---> NULL  */
                                                /*          |_________|      |_________|             */
}
```
3. Single Circular Linked List 
   - It is same as single linked list and only addition is last node's next reference point to first node. 
```
   class SingleListNode<T> {                    /*           _________        _________              */
            private T data;                     /*          |   data  |      |   data  |             */
                                                /*          |_________|      |_________|             */
            private Node<T> nextNode;           /*     ---> |   next  |  --->|   next  |  ---        */
                                                /*    |     |_________|      |_________|     |       */
                                                /*    |______________________________________|       */    
    }
```    
4. Double Circular Linked List 
   - It is same as double linked list and only additions are last node's next reference to refer to first node & first 
   node's previous reference to refer to last node.
```
    class CircularListNode<T> {  
                                                /*        _________________________________          */
                                                /*       |   _________        _________    |         */
            private Node<T> prevNode;           /*       |__|   prev  |  <---|   prev  |<--|         */
                                                /*          |_________|      |_________|             */
            private T data;                     /*          |   data  |      |   data  |             */
                                                /*          |_________|      |_________|             */
            private Node<T> nextNode;           /*     ---> |   next  |  --->|   next  |  ---        */
                                                /*    |     |_________|      |_________|     |       */
                                                /*    |______________________________________|       */    

}
```