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

class Poly{
private:
    int degree;
    int coefficients[];
public:
    Poly(int n);
    void PrintPolynomial(Poly p);
};

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
    Matrix GramSchmidt(Matrix m);
    
};

//Operators and Operatior Overloading Functions For Matrix Class
Matrix operator+(Matrix m, Matrix n);
Matrix operator-(Matrix m, Matrix n);
Matrix operator*(Matrix m, Matrix n);
Matrix operator*(Matrix m,double s);
Matrix operator+(Matrix m,double s);
Matrix SwapRows(Matrix m, int RowOne, int RowTwo);
vector<double> operator-(vector<double>a, vector<double>b);
vector<double> operator+=(vector<double>a, vector<double>b);



//Vector Projection Function for Gram-Schmidt Process
vector<double> Proj(Matrix m, int a, int b);
vector<double> GetColumn(vector<vector<double>> m, int columnIndex);
vector<double> Proj(vector<double> a, vector<double>b);


//Operators For Polynomial Class:
Poly operator+(Poly p, Poly q);





#endif /* Daisy_hpp */
