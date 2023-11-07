# Simple Aged Cache Implementation

This repository contains my implementation of the Simple Aged Cache exercise, which demonstrates the application of test-driven development practices using Java. The original concept and portions of the exercise were created by [@barinek](https://github.com/barinek).

## Project Overview

The Simple Aged Cache is a memory storage system that automatically expires and removes entries after a specified duration. This implementation focuses on the core functionality required for a cache system, including:

- Adding entries with a time-to-live (TTL) value
- Retrieving entries before they expire
- Automatically expiring entries after their TTL has elapsed

## Implementation Details

I have completed the Java portion of the exercise, ensuring that all functionalities adhere to the principles of test-driven development. The following is a brief overview of the implementation:

- **ExpirableEntry Inner Class**: Used to encapsulate cache entries along with their expiration metadata.
- **Custom Collection Handling**: Instead of relying on built-in collection classes, I implemented custom logic to manage cache entries, providing a more granular control over the expiration mechanism.

## Running the Project

To run the project and test the implementation, follow these steps:

1. Clone the repository to your local machine.
2. Navigate to the project directory in your terminal.
3. Execute the following command to run the unit tests:

```bash
./gradlew clean build
```

This command performs a clean build of the project and runs all the unit tests. A successful build indicates that all tests are passing and the cache implementation is functioning as expected.

## Download and Challenge

You can download the codebase and attempt the challenge on your own from [here](https://colorado.initialcapacity.io/contents/simple-aged-cache).

## Contributing

Contributions to enhance the functionality or improve the existing implementation are welcome. If you wish to contribute, please:

1. Fork the repository.
2. Create a new branch for your feature or fix.
3. Commit your changes with clear, descriptive messages.
4. Push the branch to your fork.
5. Open a pull request against this repository.

## License

This project is open-sourced under the MIT license. Feel free to use it as a starting point for your own projects or to contribute back to the original repository.

---

Thank you for taking an interest in this project, and I hope you find the implementation useful for your learning or development needs.

Special thanks to [@barinek](https://github.com/barinek) for the original creation and contributions to the exercise.