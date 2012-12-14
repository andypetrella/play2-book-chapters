package comparison;

public class PatternMatching {
   
    //432 chars 
    public static String switchOnInt(int value) {
        String result = null;
        switch (value) {
            case 1: result = "one";
                    break;
            case 2: result = "two";
                    break;
            case 3: result = "three";
                    break;
            case 4: result = "four";
                    break;
            default: result = "many";
        }
        return result;
    }

    //715 chars
    public static class Data {
        public int i;
        public boolean b;

        public Data(int i, boolean b) {
            this.i = i;
            this.b = b;
        }
    }
    public static String checkDomainObject(Data data) {
        String result = null;
        if (!data.b) {
            result = "disqualified";
        } else {
            switch(data.i) {
                case 1: result = "first";
                        break;
                case 2: result = "second";
                        break;
                case 3: result = "third";
                        break;
                default: result = (data.i < 100)?"correct":"finished";
            }
        }
        return result;
    }

}