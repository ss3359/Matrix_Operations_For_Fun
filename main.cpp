//
//  main.cpp
//  FLOWERS
//
//  Created by Owner on 10/15/24.
//



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
#include "Marigold.hpp"
#include "Daisy.hpp"
using namespace std;

const double π=3.14159265358;

template <class N>
class Circle {
private:
    N radius;
    N diameter;
    
public:
    N GetArea();
};


int main(){
    Matrix m(3,3);
    double detM=m.Determinant(m);
    cout<<endl;
    m.PrintMatrix(m);
    cout<<"Detmerinant of Matrix: "<<detM<<endl;

    return 0;
}


/*Excess Code:
 Complex a(2,0);
 cout<<"Gamma Function Of ";
 PrintComplex(a);
 cout<<" is: ";
 PrintComplex(Gamma(a));
 cout<<"\n\n";
 
 
 cout<<"Riemann Zeta Function For ";
 PrintComplex(a);
 cout<<" is ";
 CalculateRiemannZetaFunction(a);
 cout<<endl;
 */
