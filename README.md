# Test Automation with TestNG

### `Features:`
* POM pattern design
* Base Data Provider
* Custom Annotation Usage
* Various test runners (xml files)
* Logs
* Extent Report


## [Test Execution](https://maven.apache.org/surefire/maven-surefire-plugin/examples/single-test.html)

**Running a specific test class**

> mvn test -Dtest=Test/LoginTests.java

**Running a specific test case in a class**

> mvn test -Dtest=Test/LoginTests.java#loginTestWithInvalidCredentials

**Running a single xml**

> mvn clean test -DsuiteXmlFile=testng.xml
>
> mvn clean verify -Drunner=testng.xml

**Running all the xml files mentioned in the pom.xml:**

> mvn clean test -DsuiteXmlFile

All of the tests are independent from each other, UNLESS we create dependency.


## [maven-surefire-plugin](https://maven.apache.org/surefire/maven-surefire-plugin/examples/testng.html)

The plugin `maven-surefire-plugin` is used to configure and execute the tests and generate test reports.

###### Sample:
```xml
<plugin>
    <groupId>org.apache.maven.plugins</groupId>
    <artifactId>maven-surefire-plugin</artifactId>
    <version>${maven-surefire-plugin-version}</version>
       <configuration>
          <suiteXmlFiles>
              <suiteXmlFile>testng.xml</suiteXmlFile>          --> enter path
          </suiteXmlFiles>
          <parallel>methods</parallel>                  --> optional | for parallel execution
          <threadCount>10</threadCount>                 --> optional | for parallel execution
       </configuration>
</plugin>
```

```xml
<plugin>     
    <groupId>org.apache.maven.plugins</groupId>
    <artifactId>maven-surefire-plugin</artifactId>
    <version>3.1.2</version>
    <configuration>
        <groups>P0,P1</groups>
        <excludedGroups>Flaky</excludedGroups>      --> optional
    </configuration>
</plugin>
```


## [@DataProvider annotation]()

* The @DataProvider annotation is used to provide test data to the tests.
* It allows you to separate the test data from the test logic.
* With @DataProvider, your test data can be reusable.
* Basically we have a method annotated with @DataProvider that returns a two dimensional array object containing the data.
* We specify a name for our data provider, and we it to link the dataprovider to the test.

Example of usage:

```java
@DataProvider(name = "loginData")
public Object[][] provideLoginData() {
    return new Object[][] {
        { "user1", "password1" },
        { "user2", "password2" },
        { "user3", "password3" }
    };
}

@Test(dataProvider = "loginData")
public void testLogin(String username, String password) {
    // ...
}
```

In our framework, we have also a `BaseDataProvider` class where we store the test data.
This class is **extended** by the `BaseTest` class.
 