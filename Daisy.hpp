//
//  Daisy.hpp
//  FLOWERS
//
//  Created by Owner on 10/21/24.
//

#ifndef Daisy_hpp
#define Daisy_hpp

#include <stdio.h>
#include "Marigold.hpp"
#include <stdio.h>
#include <iostream>
#include <cmath>
#include <vector>
#include <thread>
#include <iomanip>
#include <complex>

#include "Rose.hpp"
#include "Petunia.hpp"
#include "Carnation.hpp"
using namespace std;

class Matrix{
private:
    int rows,columns;
    vector<vector<double>> data;
public:
    Matrix(int r,int c);
    Matrix(vector<vector<double>> d); 
    int GetRows();
    int GetColumns();
    vector<vector<double>> GetData();
    
    
    Matrix EnterValues(Matrix m);
    void PrintMatrix(Matrix m);
    Matrix RREF(Matrix m);
    double Determinant(Matrix m);
    Matrix I(Matrix m);
    Matrix RemoveElementFromMatrix(Matrix m,int i, int j);
    
};

//Operator Overloading For Matrix Class
Matrix operator+(Matrix m, Matrix n);
Matrix operator-(Matrix m, Matrix n);
Matrix operator*(Matrix m, Matrix n);
Matrix operator*(Matrix m,double s);
Matrix operator+(Matrix m,double s);
#endif /* Daisy_hpp */
