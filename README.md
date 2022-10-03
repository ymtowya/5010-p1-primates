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
Like `import housing.MonkeySanctuary` and call all its functions. <br />
There are several functions in it that will return complicated Map Object, because we designed it to be so for multi-layer information.<br />
It would be a great idea to `import org.json.JSONObject;` by configuring `Maven` and call 
```
JSONObject json = new JSONObject(map);
System.out.println(json);
```
to see the bigger image of the Map structure.

### Examples

The Example basically initiated the sanctuary and moved these animals here and there, and print their details.
You don't need to give inputs, the Driver Class takes care of everything.
You can change the parameters in the `setUp()` function.

### Design

One BIG change I made was I re-designed my model this morning and replaced the `List<Isolation>` and `List<Enclosure>` with two `List<AbstractMonkeyHousing>` and basically re-implement all my functions in these housing classes. The great fact about this change is that it enforces me to put all functions into the abstract class, making my degin really readable and organized! No duplicate!

### Assumptions

* MonkeyHousing only welcomes monkeys. No other animals.
* Users of this class will need a returned Map rather than a large String because they might need to reformat the information.
* Food are in integer amount.

### Limitations

* The Housing can only expand, not in reverse (i.e. the animal can move out, but the housing unit cannot be dropped.)

### Citations

It is not part of this project, but I cited the [JSONObject](https://stackoverflow.com/questions/12155800/how-to-convert-hashmap-to-json-object-in-java) from Stackoverflow in my README file to better assist any users to understand the structure of the returned Map.