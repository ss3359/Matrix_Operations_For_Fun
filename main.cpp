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
#include <algorithm>
#include <array>
#include "Rose.hpp"
#include "Petunia.hpp"
#include "Carnation.hpp"
#include "Marigold.hpp"
#include "Daisy.hpp"
#include "Azalea.hpp"
using namespace std;





int main(){
    Matrix m(2,2);
   
    m.PrintMatrix(m);
    cout<<endl<<endl;
    Matrix result=m.GramSchmidt(m);
    
    cout<<"Gram Schmidt Process Of The Matrix: "<<endl;
    result.PrintMatrix(result);
    return 0;
}


/*Excess Code:
 int a[]={10,20,30,40};
 int b[]={2,4,5,7,9,11};
 int L1 = end(a)-begin(a), L2=end(b)-begin(b),LT=L1+L2;
 int total[L1+L2];
 
 
 for(int j=0; j<sizeof(total); j++){
     if(j>=L1){
         total[j]=b[(j+L2)%LT];
     }
     else{
         total[j]=a[j];
     }
 }
 
 for(int j=0; j<L1+L2; j++){
     cout<<total[j]<<" ";
 }
 

 return 0;
 Tree* t=new Tree();
 
 t=CreateBranch("T");
 t->LeftBranch=CreateBranch("A");
 t->RightBranch=CreateBranch("S");
 t->LeftBranch->LeftBranch=CreateBranch("W");
 t->LeftBranch->RightBranch=CreateBranch("D");
 t->LeftBranch->LeftBranch->LeftBranch=CreateBranch("J");
 
 PrintTree(t);

 
 Node* head=new Node();
 Node* second=new Node();
 Node* third=new Node();
 Node* fourth=new Node();

 
 head->data=5;
 second->data=7;
 third->data=9;
 fourth->data=11;
 
 head->next=second;
 second->next=third;
 third->next=fourth;
 fourth->next=nullptr;

 PrintList(head);
 
 //Function For Polynomial Class:
 Poly::Poly(int n){
     for(int i=0; i<n; i++){
         cout<<"Enter Coefficent For X^"<<i<<endl;
         cin>>coefficients[i];
     }
 }

 void Poly::PrintPolynomial(Poly p){
     for(int d=0; d<p.degree; d++){
         cout<<coefficients[d]<<"x^"<<d;
     }
 }
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
