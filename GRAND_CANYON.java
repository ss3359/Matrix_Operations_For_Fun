/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author owner
 */
import java.util.*;
import java.util.Scanner;
import java.util.Vector;
import javax.swing.*;
import java.math.*;

class Matrix {

    int rows;
    int columns;

    double[][] M;

    public Matrix(int m, int n) {
        rows = m;
        columns = n;

        M = new double[rows][columns];
    }

    public void PrintMatrix(double[][] M_Mat) {

        for (int i = 0; i < M_Mat.length; i++) {
            for (int j = 0; j < M_Mat[0].length; j++) {

                System.out.printf(M_Mat[i][j] + "\t");

            }
            System.out.println();
        }

    }

    public void PrintMatrix(double[] M_Mat) {

        for (int i = 0; i < M_Mat.length; i++) {

            System.out.printf(M_Mat[i] + "\t");

        }
    }

    public double[][] InputEntries() {

        Scanner s = new Scanner(System.in);
        System.out.println("Enter the entries in your matrix");
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                M[j][i] = s.nextDouble();
            }
            System.out.println();
        }
        return M;
    }

    public double[] InputEntriesVector() {
        double[] v = new double[rows];
        Scanner s = new Scanner(System.in);
        System.out.println("Enter the entries in your vector");

        for (int i = 0; i < rows; i++) {
            v[i] = s.nextDouble();
        }
        return v;
    }

    double[][] add(Matrix A, Matrix B) {
        double[][] A_Mat = A.M, B_Mat = B.M;
        int m1 = A.rows, m2 = B.rows, c1 = A.columns, c2 = B.columns;
        double[][] result = new double[m1][c1];
        try {
            if (m1 != m2 || c1 != c2) {
                throw new Exception("Error");
            }
            for (int i = 0; i < m1; i++) {
                for (int j = 0; j < c1; j++) {
                    result[i][j] = A_Mat[i][j] + B_Mat[i][j];
                }
            }

        } catch (Exception e) {
            System.out.println("The matrices are not equal in size");
        }
        return result;
    }

    double[][] subtract(Matrix A, Matrix B) {
        double[][] A_Mat = A.M, B_Mat = B.M;
        int m1 = A.rows, m2 = B.rows, c1 = A.columns, c2 = B.columns;
        double[][] result = new double[m1][c1];
        try {
            if (m1 != m2 || c1 != c2) {
                throw new Exception("Error");
            }

            for (int i = 0; i < m1; i++) {
                for (int j = 0; j < c1; j++) {
                    result[i][j] = A_Mat[i][j] - B_Mat[i][j];
                }
            }

        } catch (Exception e) {
            System.out.println("The matrices are not equal in size");
        }
        return result;
    }

    double[][] multiply(Matrix A, Matrix B) {
        double[][] A_Mat = A.M, B_Mat = B.M;
        int m1 = A.rows, m2 = B.rows, c1 = A.columns, c2 = B.columns;
        double[][] result = new double[m1][c2];
        try {
            if (c1 != m2) {
                throw new Exception("Error");
            }

            for (int i = 0; i < m1; i++) {
                for (int j = 0; j < c1; j++) {
                    double sum = 0;
                    for (int k = 0; k < c1; k++) {
                        sum += A_Mat[i][k] * B_Mat[k][j];
                    }
                    result[i][j] = sum;
                }
            }

        } catch (Exception e) {
            System.out.println("Matrix Multiplication Cannot Happen!");
        }
        return result;
    }

    double[][] multiply(double[][] A, double[][] B) {

        int m1 = A.length, m2 = B.length, c1 = A[0].length, c2 = B[0].length;
        double[][] result = new double[m1][c2];
        try {
            if (c1 != m2) {
                throw new Exception("Error");
            }

            for (int i = 0; i < m1; i++) {
                for (int j = 0; j < c1; j++) {
                    double sum = 0;
                    for (int k = 0; k < c1; k++) {
                        sum += A[i][k] * B[k][j];
                    }
                    result[i][j] = sum;
                }
            }

        } catch (Exception e) {
            System.out.println("Matrix Multiplication Cannot Happen!");
        }
        return result;
    }

    public double[] multiply(double c, double[] a) {

        double[] result = new double[a.length];
        for (int i = 0; i < a.length; i++) {
            result[i] = c * a[i];
        }
        return result;
    }

    public double[] AddVectors(double[] a, double[] b) {

        double[] result = new double[a.length];
        for (int i = 0; i < a.length; i++) {
            result[i] = a[i] + b[i];
        }
        return result;
    }

    public double[] SubtractVectors(double[] a, double[] b) {

        double[] result = new double[a.length];
        for (int i = 0; i < a.length; i++) {
            result[i] = a[i] - b[i];
        }
        return result;
    }

    public double norm(Matrix A) {
        double[][] A_Mat = A.M;
        int m = A.rows, n = A.columns;
        double result = 0;
        try {
            if (n != 0) {
                throw new Exception("Error!");
            }

            for (int i = 0, j = 0; i < m; i++) {
                result += A_Mat[i][j] * A_Mat[i][j];
            }

        } catch (Exception e) {
            System.out.println("This is not a vector");
        }

        return Math.sqrt(result);
    }

    public double[][] multiply(double c, Matrix B) {
        int m = B.rows, n = B.columns;

        double[][] B_Mat = B.M, result = new double[m][n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                result[i][j] = c * B_Mat[i][j];
            }
        }

        return result;
    }

    //This is the following Gaussian Operation
    // Ri + c*(Rj) -> Ri
    public double[][] AddAndMultiplyRows(double[][] A, int Ri, int Rj, double c) {

        for (int j = 0; j < A[0].length; j++) {
            A[Ri][j] += c * A[Rj][j];
        }

        return A;
    }

    //Multiply a single row
    //c*Ri -> Ri
    public double[][] MultiplyRow(double[][] A, int Ri, double c) {

        for (int j = 0; j < A[0].length; j++) {
            A[Ri][j] = c * A[Ri][j];
        }

        return A;
    }

    //Switch out two rows; Ri <-> Rj
    public double[][] SwitchRows(double[][] A, int Ri, int Rj) {
        for (int j = 0; j < A[0].length; j++) {

            double temp = A[Ri][j];
            A[Ri][j] = A[Rj][j];
            A[Rj][j] = temp;
        }

        return A;
    }

    public double[][] RREF(Matrix A) {

        int m = A.rows, n = A.columns;
        double[][] A_Mat = A.M;
        double[][] RREF_A = A_Mat;
        int LeadRow = 0;

        for (int j = 0; j < n; j++) {

            if (LeadRow >= m) {
                break;
            }

            //Find A Pivot Entry; 
            double pivot = 0.0;
            int pivot_row = -1;

            //Find a pivot position
            for (int k = LeadRow; k < m; k++) {
                if (RREF_A[k][j] != 0) {
                    pivot_row = k;
                    break;
                }
            }
            if (pivot_row == -1) {
                continue;
            }

            //Swap Two Rows whose entry is 1. 
            RREF_A = SwitchRows(RREF_A, LeadRow, pivot_row);

            //Scale Up The Indicated Row With Pivot Position so that the Pivot Entry is 1.
            RREF_A = MultiplyRow(RREF_A, LeadRow, 1 / (RREF_A[LeadRow][j]));

            //Get entries above and below the pivot entry equal zero
            for (int r = 0; r < m; r++) {
                if (r != LeadRow) {
                    double factor = RREF_A[r][j];
                    RREF_A = AddAndMultiplyRows(RREF_A, r, LeadRow, -factor);
                }

            }
            LeadRow++;
        }

        return RREF_A;
    }

    public double[][] Adjutant(double[][] M, int m0, int n0) {

        int m = M.length, n = M[0].length;
        double[][] Aij = new double[m - 1][n - 1];

        int r = 0;
        for (int i = 0; i < m; i++) {
            if (i == m0) {
                continue;
            }
            int c = 0;
            for (int j = 0; j < n; j++) {
                if (j == n0) {
                    continue;
                }
                Aij[r][c] = M[i][j];
                c++;
            }
            r++;
        }
        return Aij;
    }

    public double Determinant(double[][] M) {

        int n = M.length;

        if (n <= 1) {
            return M[0][0];
        } else if (n == 2) {
            return M[0][0] * M[1][1] - M[0][1] * M[1][0];
        }
        double det = 0.0;
        for (int j = 0; j < n; j++) {
            double[][] Aj = Adjutant(M, 0, j);
            det += Math.pow(-1, j) * M[0][j] * Determinant(Aj);
        }

        return det;
    }

    public double DOT(double[] A, double[] B) {

        int m1 = A.length;
        int m2 = B.length;

        if (m1 != m2) {
            return 404;
        }

        double sum = 0;

        for (int i = 0; i < m1; i++) {
            sum += A[i] * B[i];
        }

        return sum;
    }

    //vector projection of a onto b
    public double[] projection(double[] a, double[] b) {

        double e = Math.max(DOT(b, b), 1e-7);
        double c = DOT(a, b) / e;

        double[] proj = multiply(c, b);

        return proj;
    }

    public double[] GetColumn(double[][] A, int c) {
        int m = A.length;

        double[] ColumnVector = new double[A.length];
        for (int i = 0; i < m; i++) {

            ColumnVector[i] = A[i][c];
        }
        return ColumnVector;
    }

    public double[][] GramSchmidt(Matrix A) {
        double[][] A_Mat = A.M;
        int m = A.rows, n = A.columns;

        double[][] O = new double[m][n];

        double[] uj = new double[m];

        for (int k = 0; k < n; k++) {
            double[] sum = new double[m];
            if (k == 0) {
                uj = GetColumn(A_Mat, 0);
            }
            double[] vk = GetColumn(A_Mat, k);

            for (int j = 0; j < k; j++) {
                uj = GetColumn(O, j);
                double[] p = projection(vk, uj);
                sum = AddVectors(sum, p);
            }

            uj = SubtractVectors(vk, sum);

            for (int i = 0; i < m; i++) {
                O[i][k] = uj[i];
            }

        }

        int p = 0;
        for (int j = 0; j < n; j++) {
            double[] uj_n = GetColumn(O, j);
            double c_norm = Math.sqrt(DOT(uj_n, uj_n));
            if (c_norm < 1e-7) {
                continue;
            }
            uj_n = multiply(1.0 / c_norm, uj_n);
            for (int i = 0; i < m; i++) {
                O[i][p] = uj_n[i];
            }
            p++;
        }

        return O;
    }

    public double[][] R_Matrix(Matrix A) {

        int m = A.rows, n = A.columns;
        double[][] A_Mat = A.M;
        double[][] R = new double[m][n];
        double Q[][] = GramSchmidt(A);

        for (int i = 0; i < m; i++) {
            double[] ei = GetColumn(Q, i);
            for (int j = 0; j < n; j++) {
                if (i > j) {
                    R[i][j] = 0;
                }
                double[] ej = GetColumn(A_Mat, j);

                R[i][j] = DOT(ei, ej);
            }
        }
        return R;
    }

    double[][] Tanspose(Matrix A) {
        double[][] A_Mat = A.M;
        int m = A.rows, n = A.columns;

        double[][] AT_Mat = new double[n][m];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                AT_Mat[i][j] = A_Mat[j][i];
            }
        }
        return AT_Mat;
    }

    double[][] Inverse(Matrix A) {
        double[][] A_Mat = A.M;
        int m = A.rows, n = A.columns;
        double Det= Determinant(A_Mat);

        if (Det == 0) {
            System.out.println("This matrix is simgular (not invertible)! ");
            return null;
        }
        System.out.printf("Determinant: "+ Det+"\n");
        
        Matrix InvA = new Matrix(m, 2*n);
        double[][] InvA_Mat = InvA.M;
        int new_length=2*n;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < InvA_Mat[0].length; j++) {
                if (j >= n && j < InvA_Mat[0].length){
                    if(i==j-n)
                        InvA_Mat[i][j]=1;
                    else
                        InvA_Mat[i][j]=0;
                }
                else
                    InvA_Mat[i][j]=A_Mat[i][j];
            }
        }
        
        PrintMatrix(InvA_Mat);
        System.out.println();
        InvA_Mat=RREF(InvA);
//        double[][] InvA_Mat2=new double[n][n]; 
//        for(int i=0; i< n; i++){
//            for(int k=0; k<n; k++){
//                
//                InvA_Mat2[i][j]=InvA_Mat[i][n+(new_length-n)*k];
//            }
//        }
        PrintMatrix(InvA_Mat);
        
        return InvA_Mat;
    }

    void Eigenvalues(Matrix A) {
        double[][] A_Mat = A.M;
        int m = A.rows, n = A.columns;
        int max = 100;
        for (int iter = 0; iter < max; iter++) {
            double[][] Q = GramSchmidt(A);
            double[][] R = R_Matrix(A);
            double[][] A_Next = multiply(R, Q);
            A.M = A_Next;

            System.out.printf("Iter " + iter + "\n");
            PrintMatrix(A_Next);
        }

    }
}

public class GRAND_CANYON {

    public static void main(String args[]) {

        Matrix A = new Matrix(3, 3);
        double[][] A_Mat = A.InputEntries();
        
        double[][] InvA= A.Inverse(A);
       
    }
}


/*
Excess Code 
double[][] Q = A.GramSchmidt(A);

        System.out.println("GS For Matrix A");
        A.PrintMatrix(Q);

        System.out.println();
        for (int i = 0; i < Q.length; i++) {
            for (int j = 0; j < Q[0].length; j++) {

                double[] vi = A.GetColumn(Q, i), vj = A.GetColumn(Q, j);

                double DOT = A.DOT(vi, vj);

                System.out.printf("v_" + i + " dot v_" + j + " = " + DOT);
                System.out.println();
            }

        }
        System.out.println();
        double[][] R = A.R_Matrix(A);
        System.out.println("R Matrix: ");
        A.PrintMatrix(R);
        System.out.println();

        A.PrintMatrix(A.multiply(Q, R));
    }


 A.PrintMatrix(A_Mat);
        System.out.println();

        A.Eigenvalues(A);
        
*/




/*

#include <gtest/gtest.h>

#include <exception>          // must be before the helper that uses std::exception
#include <type_traits>        // for std::is_base_of, std::is_nothrow_*
#include <string>             // for std::string


#include "mppe/traj/TrajectoryException.h"

static std::string what_str(const std::exception& e)
{
    // `e.what()` returns a C‑string (may be nullptr for a default‑constructed exception)
    return e.what() ? std::string(e.what()) : std::string{};
}



// Verify that TrajectoryException really derives from std::runtime_error
static_assert(std::is_base_of<std::runtime_error,
                              mppe::TrajectoryException>::value,
              "TrajectoryException must derive from std::runtime_error");

// Verify that the default constructor is declared noexcept
static_assert(std::is_nothrow_default_constructible<mppe::TrajectoryException>::value,
              "Default ctor must be noexcept");


TEST(TrajectoryException, DefaultConstructor)
{
    mppe::TrajectoryException ex;               // calls ctor(v = -1, s = nullptr)

    EXPECT_EQ(ex.value(), -1);                  // default value
    EXPECT_EQ(what_str(ex), "");                // empty message
}

TEST(TrajectoryException, MessageOnlyConstructor)
{
    const char* msg = "trajectory failure";
    mppe::TrajectoryException ex(msg);

    EXPECT_EQ(ex.value(), -1);                  // value stays at default
    EXPECT_EQ(what_str(ex), std::string(msg));
}


TEST(TrajectoryException, FullConstructor)
{
    const char* msg = "out of bounds";
    const std::int32_t code = 42;

    mppe::TrajectoryException ex(code, msg);

    EXPECT_EQ(ex.value(), code);
    EXPECT_EQ(what_str(ex), std::string(msg));
}


TEST(TrajectoryException, FullConstructor_NullMessage)
{
    const std::int32_t code = -99;
    mppe::TrajectoryException ex(code, nullptr);

    EXPECT_EQ(ex.value(), code);
    EXPECT_EQ(what_str(ex), "");               // empty string
}


TEST(TrajectoryException, CopyConstructor)
{
    const char* msg = "copy test";
    const std::int32_t code = 7;

    mppe::TrajectoryException original(code, msg);
    mppe::TrajectoryException copy(original);   // copy ctor

    EXPECT_EQ(copy.value(), original.value());
    EXPECT_EQ(what_str(copy), what_str(original));
}

TEST(TrajectoryException, AssignmentOperator)
{
    mppe::TrajectoryException src(123, "src");
    mppe::TrajectoryException dst;   // default‑constructed (value = -1, empty msg)

    dst = src;                       // copy‑assignment (compiler‑generated)

    EXPECT_EQ(dst.value(), src.value());

    // Two ways to compare the stored messages:
    //  • using the helper we wrote:
    EXPECT_EQ(what_str(dst), what_str(src));
    //  • or directly with Google‑Test’s built‑in C‑string comparator:
    // EXPECT_STREQ(dst.what(), src.what());
}



*/
