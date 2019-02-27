# RestWordFinder
Rest API for finding instances of words from a provided dictionary in a provided grid of NxN characters

Requirement assumptions and implementation details:
    The rest API accepts and returns the application/json media type
    The user provides values for N, the dictionary of words to search, and the grid on which to perform the search in a post request
    The value of the N dimensions in the NxN grid is provided in the field 'gridSize'
    The list of words in the search dictionary are provided in the 'dictionary' array
    Words in the provided dictionary and characters in the gridString are case insensitive
    The 'gridString' key lists characters in the NxN grid from left to right, top to bottom
    Spaces provided in the gridString indicate a grid square with no character present
    If the gridString length is less then NxN, trailing grid squares are left blank (as if the gridString had trailing spaces)
    If the gridString length is greater than NxN, an error is returned
