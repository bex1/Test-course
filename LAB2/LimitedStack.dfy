// A LIFO queue (aka a stack) with limited capacity.
class LimitedQ{

      var capacity : int; // capacity, max number of elements allowed on the stack.
      var arr : array<int>; // contents of stack.
      var top : int; // The index of the top of the stack, or -1 if the stack is empty

      // This predicate express a class invariant: All objects of this calls should satisfy this.
      predicate Valid()
      reads this;
      {
        arr != null && capacity > 0 && arr.Length == capacity && -1 <= top < capacity
      }

      predicate Empty()
      reads this`top;
      {
        top == -1
      }

      predicate Full()
      reads this`top, this`capacity;
      {
        top == capacity - 1
      }
      
      method Init(c : int)
      modifies this;
      requires c > 0;
      ensures fresh(arr); // ensures arr is a newly created object.
            ensures (capacity == c) && Valid() && Empty();
      {
        capacity := c;
        arr := new int[c];
        top := -1;
      }


     
      method isEmpty() returns (res : bool)
      requires Valid();
      ensures Valid() && res <==> Empty();
      {
        res := top == -1;
      }



      // Returns the top element of the stack, without removing it.
      method Peek() returns (elem : int)
      requires Valid() && !Empty();
      ensures Valid() && top == old(top) && arr[top] == elem; // top == old(top)  is prolly unessesary since we have no modifies clause anyways
      {
        elem := arr[top];
      }



      // Pushed an element to the top of a (non full) stack. 
      method Push(elem : int)
<<<<<<< HEAD
      modifies this, this.arr;
=======
      modifies this`top, arr;
>>>>>>> e518b7c20608bd41bf6c3c8d91eff01ce653dff3
      requires Valid() && !Full();
      ensures Valid() && top == old(top) + 1 && arr[top] == elem;
	  ensures forall i :: 0 <= i < top ==> arr[i] == old(arr[i]);
      {
        top := top + 1;
        arr[top] := elem;
      }

      // Pops the top element off the stack.
      method Pop() returns (elem : int)
      modifies this`top;
      requires Valid() && !Empty();
      ensures Valid() && top == old(top) - 1 && old(arr[top]) == elem; // from Martin
	  ensures Valid() && old(arr[old(top)]) == elem && top == old(top) - 1; // from Daniel
      {
        elem := Peek(); // From Martin
		elem := arr[top]; // From Daniel
		top := top - 1;
      }

      //Push onto full stack, oldest element is discarded.
      method Push2(elem : int)
	 modifies arr;
      requires Valid();
      ensures Valid() && arr[top] == elem
      ensures forall k :: 0 <= k < capacity - 1 ==> arr[k] == old(arr[k+1]);
      {
	    if (Full())
		{
		  var i: int := 0;
		  while (index  < capacity - 1) 
		  	  invariant 0 <= index  < capacity;
			  invariant forall k :: 0 <= k < index  ==> arr[k] == old(arr[k+1]);
		  {
			  arr[i] := arr[i+1];
			  i := i + 1;
		  }
		  arr[top] := elem;
        }
		else
		{
		  Push(elem);
		}
	  }

/*

// When you are finished,  all the below assertions should be provable. 
// Feel free to add extra ones as well.
      method Main(){
           var s := new LimitedStack;
           s.Init(3);

           assert s.Empty && !s.Full; 

           s.Push(27);
           assert !s.Empty;

           var e := s.Pop();
           assert e == 27;

           s.Push(5);
           s.Push(32);
           s.Push(9);
           assert s.Full;

           var e2 := s.Pop();
           assert e2 == 9 && !s.Full; 
           assert s.arr[0] == 5;

           s.Push(e2);
           s.Push2(99);

           var e3 := s.Peek();
           assert e3 == 99;
           assert s.arr[0] == 32;
                     
       }
*/
}