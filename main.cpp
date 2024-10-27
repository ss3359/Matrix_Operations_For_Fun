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


int GCF(int a,int b){
    int factor=1,n=1;
    
    while(n <= a || n<=b){
        if(a%n==0 && b%n==0){
            factor=n;
        }
        n++;
    }
    return factor;
}

int main(){
    

    Matrix m(3,4);
//    double detM=m.Determinant(m);
//    cout<<endl;
    m.PrintMatrix(m);
//    cout<<"Detmerinant of Matrix: "<<detM<<endl;

    cout<<"\n\n";
    cout<<"Row Reduced Echelon Form: "<<endl;
    Matrix rrefM=m.RREF(m);
    rrefM.PrintMatrix(rrefM);
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
