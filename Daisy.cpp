//
//  Daisy.cpp
//  FLOWERS
//
//  Created by Owner on 10/21/24.
//

#include "Daisy.hpp"
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

//Basic Function in Matrix Class
Matrix::Matrix(int r,int c){
    rows=r;
    columns=c;
    
    cout<<"Enter Values For Matrix: "<<endl;
    double value;
    for(int i=0;i<rows; i++){
        vector<double> ROWS;
        for(int j=0; j<columns; j++){
            cin>>value;
            ROWS.push_back(value);
        }
        data.push_back(ROWS);
    }
}
Matrix::Matrix(vector<vector<double>> D){
    data=D;
}

int Matrix::GetRows(){
    return rows;
}
int Matrix::GetColumns(){
    return columns;
}

vector<vector<double>> Matrix:: GetData(){
    return data;
}

void Matrix::PrintMatrix(Matrix m){
    vector<vector<double>> data=m.GetData();
    for(int i=0; i<data.size(); i++){
        for(int j=0;j<data[i].size();j++){
            cout<<data[i][j]<<"\t";
        }
        cout<<endl;
    }
}

Matrix Matrix::I(Matrix m){
    vector<vector<double>> IData, MData=m.GetData();
    for(int i=0; i<MData.size(); i++){
        vector<double> ROWS;
        for(int j=0; j<MData[i].size(); j++){
            if(i==j){
                ROWS.push_back(1);
            }
        }
        IData.push_back(ROWS);
    }
    Matrix I(IData);
    
    return I; 
}






//Operatior Overloading For Matrix Class
Matrix operator+(Matrix m, Matrix n){
    vector<vector<double>> RD;
    try{
        if(m.GetRows()==n.GetRows() && m.GetColumns()==n.GetColumns()){
            vector<vector<double>> M=m.GetData(),N=n.GetData();
            for(int i=0; i<M.size(); i++){
                vector<double> RDRows;
                   for(int j=0; j<M[i].size(); j++){
                       RDRows.push_back(M[i][j]+N[i][j]);
                }
                RD.push_back(RDRows);
            }
        }
        else{
            throw(m.GetRows());
        }
    }catch(exception e){
        cout<<"The Dimensions Of The Matrices Are Not Equal";
    }
    Matrix result(RD); 
    return result;
}


Matrix operator-(Matrix m, Matrix n){
    vector<vector<double>> RD;
    try{
        if(m.GetRows()==n.GetRows() && m.GetColumns()==n.GetColumns()){
            vector<vector<double>> M=m.GetData(),N=n.GetData();
            for(int i=0; i<=M.size(); i++){
                   for(int j=0; j<=M[0].size(); j++){
                       RD[i][j]=M[i][j]-N[i][j];
                }
            }
        }
        else{
            throw(m.GetRows());
        }
    }catch(exception e){
        cout<<"The Dimensions Of The Matrices Are Not Equal";
    }
    Matrix result(RD);
    return result;
}


Matrix operator*(Matrix m, Matrix n){
    vector<vector<double>> RD;
    try{
        if(m.GetRows()==n.GetColumns()){
            vector<vector<double>>M=m.GetData(),N=n.GetData();
            for(int i=0; i<M.size(); i++){
                vector<double> ROWS;
                for(int j=0; j<N[0].size(); j++){
                    double sum=0;
                    for(int k=0; k<N[0].size();k++){
                        sum+=M[i][k]*N[k][j];
                    }
                    ROWS.push_back(sum);
                }
                RD.push_back(ROWS); 
            }
        }
        else{
            throw(m.GetRows());
        }
    }catch(exception e){
        cout<<"The Dimensions Of The Matrices Are Not Equal";
    }
    Matrix result(RD);
    return result;
}


//Operator Overloading For Scalar Operations

Matrix operator*(Matrix m, double s){
    vector<vector<double>> RD, MData=m.GetData();
    
    for(int i=0; i<MData.size(); i++){
        vector<double> ROWS;
        for(int j=0; j<MData[i].size(); j++){
            ROWS.push_back(s*MData[i][j]);
        }
        RD.push_back(ROWS);
    }
    Matrix result(RD);
    
    return result;
}



//Matrix Properties


double Matrix::Determinant(Matrix m){
    vector<vector<double>> MData=m.GetData();
    int rows = MData.size(),columns=MData[0].size();
    cout<<"Size of Matrix: "<<rows<<" x "<<columns<<endl;
    if(rows==1 && columns==1){
        cout<<"Base Case (1x1 Matrix): "<<endl;
        return MData[0][0];
    }
    
    if(rows==2 && columns==2){
        cout<<"Base Case (2x2 Matrix) "<<endl;
        return MData[0][0]*MData[1][1]-MData[1][0]*MData[0][1];
    }
    
    double det=0;
    
    for(int i=0; i<columns; i++){
        vector<vector<double>> SubMatrixData(rows-1,vector<double>(columns-1));
        
        for(int j=1; j<rows; j++){
            int sub_col=0;
            for(int k=0; k<columns; k++){
                if (k==i){
                    continue;
                }
                SubMatrixData[j-1][sub_col]=MData[j][k];
                sub_col++;
            }
        }
        cout<<"Submatrix created for column: "<<i<<" : "<<endl;
        for(int x=0; x<SubMatrixData.size(); x++){
            for(int y=0; y<SubMatrixData[0].size(); y++){
                cout<<SubMatrixData[x][y]<<" ";
            }
            cout<<endl;
        }
        
        Matrix SM(SubMatrixData);
        det+=pow(-1,i)*MData[0][i]*Determinant(SM);
    }
    
    return det;
}

Matrix SwapRows(Matrix m, int RowOne,int RowTwo){
    vector<vector<double>> D=m.GetData();
    int rows=D.size(),columns=D[0].size();
    for(int j=0; j<columns;j++){
        int temp=D[RowOne][j];
        D[RowOne][j]=D[RowTwo][j];
        D[RowTwo][j]=temp;
    }
    Matrix NM(D);
    
    return NM;
}

Matrix Matrix::RREF(Matrix m){
    vector<vector<double>> data=m.GetData();
    int rows=data.size(),columns=data[0].size();
    int lead=0; //initalize pivot position;
    
    
    for(int r=0; r<rows; r++){
        if(columns<=lead){
            return m;
        }
        //Step 1: Find the pivot row.
        int i=r;
        while(data[i][lead]==0){
            i++;
            if(i==rows){
                i=r;
                lead++;
                if(lead==columns){
                    return m;
                }
            }
        }
        //Step 2: Swapping Rows
        if (i!=r){
            m=SwapRows(m, i, r);
            data=m.GetData(); // The data is updated after the rows are switched. 
        }
        
        //Step 3: Scale the pivot row th make pivot element equal to 1
        double pivot=data[r][lead];
        if(pivot!=0){
            for(int j=0; j<columns; j++){
                data[r][j] /= pivot;
            }
        }
        
        //Step 4: Add/Multiply Rows
        for(int k=0; k<rows;k++){
            if(k!=r){
                double scale=data[k][lead];
                    for(int j=0; j<columns; j++){
                        data[k][j]-=scale*data[r][j];
                    }
                }
            }
            lead++;
    }
    
   
    m=Matrix(data); //The modified data is assigned to the original matrix.
    return m;
}
