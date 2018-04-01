# RAnalyzer

## About the project
Implementation phase of designing a CASE (Computer Aided Software Engineering) tools for Software Design subject's Final Project.

## About RAnalyzer
A tool for helping requirement engineer to analyze dependencies between requirements in a software design and development.
So when there is a change on a requirement, the requirement engineer can make a quick analysis and generate a dependency graph
from given requirement document and requirement statement.

This tool use Use Case Diagram (UCD) that have been exported into an .xmi file and requirement statement as an input.

## How does it work
1. Given the Use Case Diagram in .xmi format, then it is parsed using XML Parser library to extract the use case statement and store the relation in it.
2. User input the requirement statement
3. The program process the requirement statement using Natural Language Processing library
4. The use case statement (extracted from UCD) and the 'cleaned' requirement statement is both inputted into String Similarity algorithm by N to N relation
5. Use case statement and requirement statement that most similar, is considered matched.
6. Dependency graph is generated based on relation from Use Case Diagram
