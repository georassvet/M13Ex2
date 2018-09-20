package main.java;

    public class IntegerCollection {
        private Integer[] array;
        private int pos;

        private static final int DEFAULT_CAPACITY = 10;


        public IntegerCollection() {
            array = new Integer[DEFAULT_CAPACITY];
        }

        public boolean isEmpty() {
            return pos == 0;
        }

        public void add(Integer value) throws ValueNotNull {
            if (value == null) throw new ValueNotNull();

            ensureCapacity(pos+1);
            updateArray(value);       // update array values on new value
            array[pos++] = value;     // add new value
        }

        public void add(Integer value, int index) throws ValueNotNull, WrongIndex{
            if (value == null)        throw new ValueNotNull();
            if (index<0 || index>pos) throw new WrongIndex();

            ensureCapacity(pos+1);
            System.arraycopy(array,0,array,0,index+1);
            System.arraycopy(array,index,array,index+1,pos-index);
            array[index]=value;
            pos++;
        }

        private void ensureCapacity(int size){
            if (size >= array.length) {
                resize();               // resize array
            }
        }

        private void resize() {
            Integer[] newArray = new Integer[array.length*3/2+1];
            for (int i = 0; i < array.length; i++) {
                newArray[i] = array[i];
            }
            array = newArray;
        }

        private void updateArray(Integer value) {
            if(value!=null) {
                for (int i = 0; i < pos; i++) {
                    array[i] += value;
                }
            }
        }

        public void remove(Integer integer) throws ElementNotFound {
            if (pos > 0) {
                int index = getIndexByVal(integer);

                if (index == -1) {
                    throw new ElementNotFound();
                }

                Integer[] newArray = new Integer[array.length - 1];
                for (int i = 0, j = 0; i < array.length; i++) {
                    if (i == index) {
                        continue;
                    }
                    newArray[j++] = array[i];
                }
                array = newArray;
                pos--;
                updateArray(-integer);
            } else {
                System.out.println("Collection is empty");
            }
        }

        public Integer searchByIndex(int index) throws ElementNotFound {
            if (index > 0 && index < pos) {
                return getValue(index);
            } else {
                throw new ElementNotFound();
            }
        }

        public Integer searchByValue(Integer value) throws ElementNotFound {
            for (int i = 0; i < pos; i++) {
                if (array[i].equals(value))
                    return value;
            }
            throw new ElementNotFound();
        }

        public int maxValue() {
            int result = array[0];
            for (int i = 1; i < pos; i++) {
                if (result < array[i]) {
                    result = array[i];
                }
            }
            return result;
        }

        public int minValue() {
            int result = array[0];
            for (int i = 1; i < pos; i++) {
                if (result > array[i]) {
                    result = array[i];
                }
            }
            return result;
        }

        public int getAvg(){
            int sum = 0;
            int counter = 0;
            for (int i = 0; i < pos; i++) {
                sum += array[i];
                counter++;
            }
            return sum / counter;
        }

        private int getIndexByVal(Integer integer) {
            for (int i = 0; i < array.length; i++) {
                if (array[i].equals(integer)) {
                    return i;
                }
            }
            return -1;
        }

        @Override
        public String toString() {
            StringBuilder string = new StringBuilder();
            string.append("[");
            for (int i = 0; i < pos; i++) {
                string.append(array[i]).append(", ");
            }
            String result = string.toString();
            result = result.replaceFirst("[, \\t]+$", "");
            return result + "]";
        }

        private int getValue(int index) {
            return array[index];
        }
    }

