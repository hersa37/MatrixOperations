# MatrixOperations
Currently a work in progress. My own small project to make codes for various matrix operations. Other than that, it's just a nice exercise to test out various coding concepts I'm  learning

Current features:
- Calculate inverse of any size (3x3 uses row expansion, 4x4 and up uses Gauss-Jordan)
- Elementary row operations
- Gauss/Gauss-Jordan operations ~~with steps~~ Removed because current implementation feels too bulky. I'll try to find a way to make it work with current code without making it bulky
- Check vector basis
- Transform a vector from one basis to another

What I plan on expanding:

- ~~Calculate inverse of any matrix size with~~
- ~~Do elementary row operations and gauss-jordan operations~~ 
- ~~Vector space operations~~
- Eigenvalue/eigenvector

Changelog (I still don't know how to version stuff correctly)
- 0.01 - Initial code
- 0.01.1 - Added elemental row operations and Gauss/Gauss-Jordan operations
- 0.02 - Restructure code so the callable operations are all in one class and changes parameters around to help immutability of the base matrix. Adds custom exception for testing
- 0.03 adds basis calcuations. Removed implementation of exception because it's just a hassle right now


I try to not copy other people's code, though help would be great. If you ever find this, let me know what I can improve/change
