## Primates

### Overview

This is a model of a primate sanctuary, it needs to represent different kinds of housing, monkeys, and food, and other attributes. I designed a hierachy sturcture and enumeration denotion to help construct this model.

### Features
* Valid Check. 
    I worked with very solid check to make sure they can deal with exceptions.
* Organized Information
    All information are stored in `Map<String, Object>` instead of String, easy to edit and reformat.
* Clear Structure
    The structure has been re-designed so it's very clear and readable.
* Good Extension. 
    In future, it's very easy to extend with other housing formats.

### How to run

If you have JRE installed on your environment, double click the `./primates.jar` is fine enough.
The output will be in ./res/runningResult.txt

### Useage

You are encouraged to import the public classes and discover the extendable features in it.
Like `import housing.MonkeySanctuary` and call all its functions!

### Examples

You don't need to give inputs, the Driver Class takes care of everything.
You can change the parameters in the `setUp()` function.

### Design

One BIG change I made was I re-designed my model this morning and replaced the `List<Isolation>` and `List<Enclosure>` with two `List<AbstractMonkeyHousing>` and basically re-implement all my functions in these housing classes. The great fact about this change is that it enforces me to put all functions into the abstract class, making my degin really readable and organized! No duplicate!

### Assumptions

* MonkeyHousing only welcomes monkeys. No other animals.
* Food are in integer amount.

### Limitations

* I might have 'abstracted' TOO much so it's hard to change the inner class. Still trying to figure out how to save them out of the holes of hierachy.

### Citations

Not that I can think of . I did all these on my own.