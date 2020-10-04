# Dining Problem

### Description

The Dining Philosophers problem is a classic synchronization problem that
illustrates the challenge of deadlock. There is a round table with n plates
and n chopsticks. A philosopher sitting at each plate requires two chopsticks 
to eat. Suppose that each philosopher proceeds by grabbing the chopstick on 
the left, grabbing the chopstick on the right, eating, and then replacing both 
chopsticks. If philosophers follow this approach they can unfortunately, enter 
a deadlock: each philosopher can grab the chopstick on the left but then be 
stuck waiting for the philosopher on the right to release the chopstick she holds.

Reference: Operating Systems: Principles and Practice (Book by Thomas Anderson 
& Michael Dahlin)

### What classes do I need to run ?

There are two versions of the program available, one
that uses semaphores explicitly and another one that uses
monitors. The classes to be executed follow the name convention:
"DinnerTableXXXX".