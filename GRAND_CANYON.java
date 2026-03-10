
inline const double* const * const * const create3DPointers(
    const double (&data)[2][3][4])  // reference to a 2×3×4 array
{
    /*  Each element of the static array below is a **double‑**pointer
        (i.e. const double* const* const).  An array of those decays to
        a **triple‑pointer** (const double* const* const* const), which
        matches the parameter type of the LookupTable overload.               */
    static const double* const * const ptrs[2] = {
        reinterpret_cast<const double* const *>(data[0]),  // row 0 → double**
        reinterpret_cast<const double* const *>(data[1])   // row 1 → double**
    };

    return ptrs;  // decays to const double* const* const* const
}

namespace
{

// LookupTable tests

TEST(testLookupTable, SetKey)
{
    mppe::LookupTable t(2, 3, 4);

    // X-dimension
    t.setKey(0, 0, 10.0);
    EXPECT_DOUBLE_EQ(t.getKey(0, 0), 10.0);
    EXPECT_THROW(t.setKey(0, 2, 10.0), std::runtime_error);

    // Y-dimension
    t.setKey(1, 0, 20.0);
    EXPECT_DOUBLE_EQ(t.getKey(1, 0), 20.0);
    EXPECT_THROW(t.setKey(1, 3, 20.0), std::runtime_error);

    // Z-dimension
    t.setKey(2, 0, 30.0);
    EXPECT_DOUBLE_EQ(t.getKey(2, 0), 30.0);
    EXPECT_THROW(t.setKey(2, 4, 30.0), std::runtime_error);

    // Invalid Dimension
    EXPECT_THROW(t.setKey(3, 0, 40.0), std::runtime_error);
}

TEST(testLookupTable, SetKeys)
{
    mppe::LookupTable t(2, 3, 4);
    const double xVals[] = {1.0, 2.0};
    const double yVals[] = {10.0, 20.0, 30.0};
    const double zVals[] = {100.0, 200.0, 300.0, 400.0};

    t.setKeys(0, 2, xVals);
    t.setKeys(1, 3, yVals);
    t.setKeys(2, 4, zVals);

    std::vector<double> out;

    t.getKeys(0, out);
    ASSERT_EQ(out.size(), 2u);
    EXPECT_DOUBLE_EQ(out[0], 1.0);
    EXPECT_DOUBLE_EQ(out[1], 2.0);

    t.getKeys(1, out);
    ASSERT_EQ(out.size(), 3u);
    EXPECT_DOUBLE_EQ(out[0], 10.0);
    EXPECT_DOUBLE_EQ(out[1], 20.0);
    EXPECT_DOUBLE_EQ(out[2], 30.0);

    t.getKeys(2, out);
    ASSERT_EQ(out.size(), 4u);
    EXPECT_DOUBLE_EQ(out[0], 100.0);
    EXPECT_DOUBLE_EQ(out[1], 200.0);
    EXPECT_DOUBLE_EQ(out[2], 300.0);
    EXPECT_DOUBLE_EQ(out[3], 400.0);

    EXPECT_THROW(t.setKeys(3, 1, xVals), std::runtime_error);
}

// The getKey tests the "no-exception" part-the value has been checked
TEST(testLookupTable, GetKey)
{
    mppe::LookupTable t(2, 3, 4);

    // Valid accesses-just make sure they don't throw
    EXPECT_NO_THROW(t.getKey(0, 0));
    EXPECT_NO_THROW(t.getKey(1, 0));
    EXPECT_NO_THROW(t.getKey(2, 0));

    // Out of range indeces should throw
    EXPECT_THROW(t.getKey(0, 2), std::runtime_error);
    EXPECT_THROW(t.getKey(1, 3), std::runtime_error);
    EXPECT_THROW(t.getKey(2, 4), std::runtime_error);
    EXPECT_THROW(t.getKey(3, 0), std::runtime_error);
}

TEST(testLookupTable, SetValue_1D)
{
    // 1‑D
    mppe::LookupTable t1(2);  // 1D nx =2

    t1.setValue(0, 10.0);
    t1.setValue(1, 20.0);

    EXPECT_DOUBLE_EQ(t1.getValue(0), 10.0);
    EXPECT_DOUBLE_EQ(t1.getValue(1), 20.0);
    EXPECT_DOUBLE_EQ(t1.getMinimumValue(), 10.0);  // default value is 0
    EXPECT_DOUBLE_EQ(t1.getMaximumValue(), 20.0);  // default value is 0
    EXPECT_THROW(t1.setValue(2, 30.0), std::runtime_error);

    // bulk array overload – overwrites the whole table
    const double bulk[2] = {5.0, 6.0};

    t1.setValue(bulk);

    EXPECT_DOUBLE_EQ(t1.getValue(0), 5.0);
    EXPECT_DOUBLE_EQ(t1.getValue(1), 6.0);
    EXPECT_DOUBLE_EQ(t1.getMinimumValue(), 5.0);
    EXPECT_DOUBLE_EQ(t1.getMaximumValue(), 6.0);

    // wrong‑dimensional bulk array (2‑D pointer) must be rejected
    const double r0[1] = {0.0};
    const double r1[1] = {0.0};
    const double* const bad2D[2] = {r0, r1};
    EXPECT_THROW(t1.setValue(bad2D), std::runtime_error);
}

TEST(testLookupTable, SetValue_2D)
{
    mppe::LookupTable t2(2, 3);  // nx = 2, ny = 3

    // element‑wise writes
    t2.setValue(0, 0, 1.0);
    t2.setValue(1, 2, 2.0);
    EXPECT_DOUBLE_EQ(t2.getValue(0, 0), 1.0);
    EXPECT_DOUBLE_EQ(t2.getValue(1, 2), 2.0);
    EXPECT_DOUBLE_EQ(t2.getMinimumValue(), 1.0);
    EXPECT_DOUBLE_EQ(t2.getMaximumValue(), 2.0);

    // out‑of‑range index on *either* axis → exception
    EXPECT_THROW(t2.setValue(2, 0, 0.0), std::runtime_error);
    EXPECT_THROW(t2.setValue(0, 3, 0.0), std::runtime_error);

    // bulk‑array overload (2‑D pointer)
    const double r0[3] = {1.0, 2.0, 3.0};
    const double r1[3] = {4.0, 5.0, 6.0};
    const double* const v2[2] = {r0, r1};

    t2.setValue(v2);

    // verify that the whole matrix was copied correctly
    EXPECT_DOUBLE_EQ(t2.getValue(0, 0), 1.0);
    EXPECT_DOUBLE_EQ(t2.getValue(0, 1), 2.0);
    EXPECT_DOUBLE_EQ(t2.getValue(0, 2), 3.0);
    EXPECT_DOUBLE_EQ(t2.getValue(1, 0), 4.0);
    EXPECT_DOUBLE_EQ(t2.getValue(1, 1), 5.0);
    EXPECT_DOUBLE_EQ(t2.getValue(1, 2), 6.0);
    EXPECT_DOUBLE_EQ(t2.getMinimumValue(), 1.0);
    EXPECT_DOUBLE_EQ(t2.getMaximumValue(), 6.0);
}

TEST(testLookupTable, SetValue_3D)
{
    mppe::LookupTable t3(2, 3, 4);  // nx = 2, ny = 3, nz = 4

    double data[2][3][4];

    for (std::size_t i = 0; i < 2; i++)
    {
        for (std::size_t j = 0; j < 3; j++)
        {
            for (std::size_t k = 0; k < 4; k++)
            {
                data[i][j][k] = static_cast<double>(i * 100 + j * 10 + k);
            }
        }
    }

    // Convert the cube into the required pointer‑to‑pointer‑to‑pointer form
    const double* const * const * const v3 = create3DPointers(data);

    // Positive Test: valid 3‑D bulk‑array write
    t3.setValue(v3);

    // element‑wise writes
    EXPECT_DOUBLE_EQ(t3.getValue(0, 0, 0), 0.0);    // 0*100 + 0*10 + 0
    EXPECT_DOUBLE_EQ(t3.getValue(0, 2, 3), 23.0);   // 0*100 + 2*10 + 3
    EXPECT_DOUBLE_EQ(t3.getValue(1, 0, 1), 101.0);  // 1*100 + 0*10 + 1
    EXPECT_DOUBLE_EQ(t3.getValue(1, 2, 3), 123.0);  // 1*100 + 2*10 + 3

    // Verify the min and max values were updated correctly
    EXPECT_DOUBLE_EQ(t3.getMinimumValue(), 0.0);
    EXPECT_DOUBLE_EQ(t3.getMaximumValue(), 123.0);

    // out‑of‑range index on *any* axis → exception
    EXPECT_THROW(t3.setValue(2, 0, 0, 0.0), std::runtime_error);
    EXPECT_THROW(t3.setValue(0, 3, 0, 0.0), std::runtime_error);
    EXPECT_THROW(t3.setValue(0, 0, 4, 0.0), std::runtime_error);

    // Negative Test: wrong‑dimensional bulk array (1‑D pointer) must be rejected
    const double* const bad2D[2] = {data[0][0], data[1][0]};  // decays to const double*
    EXPECT_THROW(t3.setValue(bad2D), std::runtime_error);
}

TEST(testLookupTable, GetValue)
{
    mppe::LookupTable t(2, 3, 4);
    t.setValue(0, 0, 0, 10.0);
    t.setValue(1, 2, 3, 20.0);

    // direct index access-the two values we set,
    EXPECT_DOUBLE_EQ(t.getValue(0, 0, 0), 10.0);
    EXPECT_DOUBLE_EQ(t.getValue(1, 2, 3), 20.0);

    // out‑of‑range checks
    EXPECT_THROW(t.getValue(2, 0, 0), std::runtime_error);
    EXPECT_THROW(t.getValue(0, 3, 0), std::runtime_error);
    EXPECT_THROW(t.getValue(0, 0, 4), std::runtime_error);
}

TEST(testLookupTable, Lookup)
{
    mppe::LookupTable t(2, 3, 4);
    t.setValue(0, 0, 0, 10.0);
    t.setValue(1, 2, 3, 20.0);

    // 1D Looks (y and z default to the first key -> 0)
    EXPECT_DOUBLE_EQ(t.lookup(-1.0), 20.0);
    EXPECT_DOUBLE_EQ(t.lookup(0.5), 5.0);
    EXPECT_DOUBLE_EQ(t.lookup(0.0), 10.0);
    EXPECT_DOUBLE_EQ(t.lookup(1.0), 0.0);

    // 3‑dimensional lookups
    EXPECT_DOUBLE_EQ(t.lookup(1.0, 2.0, 3.0), 20.0);
    EXPECT_DOUBLE_EQ(t.lookup(0.5, 1.0, 1.5), 0.0);
    EXPECT_DOUBLE_EQ(t.lookup(2.0, 3.0, 4.0), 30.0);
    EXPECT_DOUBLE_EQ(t.lookup(0.5, -1.0, -1.0), 0.0);
}

TEST(testLookupTable, GetDimensionSize)
{
    mppe::LookupTable t(2, 3, 4);
    EXPECT_NO_THROW(t.getDimensionSize(0));
    EXPECT_NO_THROW(t.getDimensionSize(1));
    EXPECT_NO_THROW(t.getDimensionSize(2));
    EXPECT_THROW(t.getDimensionSize(3), std::runtime_error);
}

TEST(testLookupTable, Assignment)
{
    // valid assignment (same dimensions)
    mppe::LookupTable a(2, 3, 4);
    mppe::LookupTable b(2, 3, 4);
    a.setValue(0, 0, 0, 42.0);
    b.setValue(1, 2, 3, 99.0);
    a = b;
    EXPECT_EQ(a.getNDimensions(), b.getNDimensions());
    EXPECT_DOUBLE_EQ(a.getValue(1, 2, 3), 99.0);
    EXPECT_THROW(a.getValue(0, 0, 0), std::runtime_error);  // a's old value should be gone

    // assignment that shrinks dimensions
    mppe::LookupTable c(2, 3, 4);
    mppe::LookupTable d(2, 3);  // 2‑D
    EXPECT_NO_THROW(c = d);
    EXPECT_EQ(c.getNDimensions(), 2u);
    EXPECT_EQ(c.getDimensionSize(1), 3u);
}

}  // namespace
