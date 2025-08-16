## 📘 Overview  

The `Account` class is the **abstract base** for all bank accounts in the Mini Banking CLI project.  
It defines the **shared structure and behavior** of accounts, including an owner, a unique ID, a balance, and the ability to deposit and withdraw money.  

This class ensures **encapsulation** (private fields with controlled access), **validation** (rejecting invalid inputs), and **polymorphism** (subclasses provide their own `type()` implementation).  

By being **abstract**, `Account` cannot be created directly; instead, specific types like `SavingsAccount` or `CheckingAccount` extend it to add their own rules (e.g., interest or transaction fees).  

In short, `Account` acts as a **blueprint** that guarantees all accounts share the same reliable core features, while still leaving room for specialized behavior in subclasses.  
