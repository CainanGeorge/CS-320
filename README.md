# CS-320
CS320 final submission
CS-320: Software Test, Automation, and Quality Assurance

Coursework from CS-320 at SNHU.

The contact service came out of Project One, where the assignment was to build back-end services with no UI or database and verify them entirely through JUnit. The report is Project Two, a write-up of the testing approach across all three services I built that term. The contact service has 30 tests between ContactTest and ContactServiceTest, and both production classes came back at 100% instruction coverage in EclEmma with no missed instructions.

Reflection
How can I ensure that my code, program, or software is functional and secure?

By testing every condition a requirement implies instead of every requirement, since those are different numbers. A phone field that must be exactly ten digits sounds like one rule, but a value can fail it three ways, and the sneaky one is a string that has the right length with the wrong characters in it. That is why testPhoneNotAllDigits exists and why it passes 602-555-12 into the constructor. The other thing I picked up is that checking whether an exception fires is only half a test, because a guard clause can throw correctly and still leave the object in a bad state. Security was quieter but followed the same idea. Every setter validates its own input so nothing trusts the caller, and the appointment service returns copies of its Date rather than the stored reference, which stops someone from mutating it after validation already passed.

How do I interpret user needs and incorporate them into a program?

The requirements document is the customer talking, so I worked from that instead of from my own idea of what the code should do. Where I could, I enforced requirements in the language rather than in a comment. The spec says a contact ID cannot be updated, so the field is final with no setter and the compiler takes care of it. What I did not expect is how incomplete requirements usually are. These set maximum lengths but never minimums, which technically makes an empty string valid. I went with that reading and wrote a test documenting it, because a judgment call nobody can see turns into an argument later.

How do I approach designing software?

One rule, one place. Validation lives in the setters, and the constructor calls those setters instead of repeating the checks, so changing a rule means editing one method. Same thinking behind the private getExisting helper in the services, which keeps the not found behavior from being copied into all four update methods. I also pick data structures for how they will actually be used, which is why both services key a HashMap on the ID. Mostly though, I came out of this course thinking about testability earlier than I used to. These services could be tested in isolation because nothing else was tangled into them, and code that is hard to test is usually telling you something about its design.
