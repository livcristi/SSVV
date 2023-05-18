package ssvv.example.validation;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.fail;

public class RegressionTest0
{

    public static boolean debug = false;

    @Test
    public void test1() throws Throwable
    {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test1");
        ssvv.example.validation.NotaValidator notaValidator0 = new ssvv.example.validation.NotaValidator();
        java.lang.Class<?> wildcardClass1 = notaValidator0.getClass();
        assertNotNull(wildcardClass1);
    }

    @Test
    public void test2() throws Throwable
    {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test2");
        ssvv.example.validation.NotaValidator notaValidator0 = new ssvv.example.validation.NotaValidator();
        ssvv.example.domain.Nota nota1 = null;
        // The following exception was thrown during execution in test generation
        try
        {
            notaValidator0.validate(nota1);
            fail("Expected exception of type java.lang.NullPointerException; message: Cannot invoke \"ssvv.example.domain.Nota.getID()\" because \"nota\" is null");
        } catch (java.lang.NullPointerException e)
        {
            // Expected exception.
        }
    }

    @Test
    public void test3() throws Throwable
    {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test3");
        java.lang.Object obj0 = new java.lang.Object();
        java.lang.Class<?> wildcardClass1 = obj0.getClass();
        assertNotNull(wildcardClass1);
    }
}

