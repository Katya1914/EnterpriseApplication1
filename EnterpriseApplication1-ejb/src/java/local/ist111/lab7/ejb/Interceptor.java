/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package local.ist111.lab7.ejb;

import javax.interceptor.AroundInvoke;
import javax.interceptor.Interceptors;
import javax.interceptor.InvocationContext;

/**
 *
 * @author USER
 */

public class Interceptor {
        @AroundInvoke 
        public Object method(InvocationContext ic)
                throws Exception
        {
            System.out.println("Метод вызвали");
            ic.proceed();
            return null;
        }
}
