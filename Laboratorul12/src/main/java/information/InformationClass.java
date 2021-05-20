package information;

import java.lang.reflect.Method;

public class InformationClass {
    private Class clasa;
    private Method[] methods;

    public InformationClass(Class clasa) {
        this.clasa = clasa;
    }

    public void setMethods() {
        this.methods = this.clasa.getMethods();
    }

    public void showMethods() {
        for(Method method : this.methods) {
            System.out.println(method.getName());
        }
    }

    public static void main(String[] args) {}
}
