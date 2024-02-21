public class Sorts {

    // Selection Sort
    public class Selection {

        public static void sort(Comparable[] a) {
            int N = a.length;
            for (int i = 0; i < N; i++) {
                int min = i;
                for (int j = i + 1; j < N; j++) {
                    if (less(a[j], a[min])) {
                        min = j;
                        exch(a, i, min);
                    }

                }
            }
        }

        private static boolean less(Comparable v, Comparable w) {
            return v.compareTo(w) < 0;
        }

        private static void exch(Comparable[] a, int i, int j) {
            Comparable swap = a[i];
            a[i] = a[j];
            a[j] = swap;
        }

    }

    // Insertion sort
    public class Insertion {

        public static void sort(Comparable[] a) {
            int N = a.length;
            for (int i = 0; i < N; i++) {
                for (int j = i; j > 0; j--) {
                    if (less(a[j], a[j - 1])) {
                        exch(a, j, j - 1);
                    } else {
                        break;
                    }
                }
            }
        }

        private static boolean less(Comparable v, Comparable w) {
            return v.compareTo(w) < 0;
        }

        private static void exch(Comparable[] a, int i, int j) {
            Comparable swap = a[i];
            a[i] = a[j];
            a[j] = swap;
        }
    }

    // Shellsort
    public class Shell {
        public static void sort(Comparable[] a) {
            int N = a.length;
            int h = 1;
            while (h < N / 3)
                h = 3 * h + 1; // 1, 4, 13, 40, 121, 364, ... 3x+1 secuencia de incrementos
            while (h >= 1) {
                // h-sort the array.
                for (int i = h; i < N; i++) {
                    for (int j = i; j >= h && less(a[j], a[j - h]); j -= h)
                        exch(a, j, j - h);
                }
                h = h / 3;
                // siguiente h
            }
        }

        private static boolean less(Comparable v, Comparable w) {
            return v.compareTo(w) < 0;
        }

        private static void exch(Comparable[] a, int i, int j) {
            Comparable swap = a[i];
            a[i] = a[j];
            a[j] = swap;
        }
    }

    // Quicksort
    public static void quicksort(int A[], int izq, int der) {
            int pivote=A[izq]; // tomamos primer elemento como pivote
            int i=izq;         // i realiza la búsqueda de izquierda a derecha
            int j=der;         // j realiza la búsqueda de derecha a izquierda
            int aux;
           
            while(i < j){                          // mientras no se crucen las búsquedas                                   
                   while(A[i] <= pivote && i < j) {i++}; // busca elemento mayor que pivote
                   while(A[j] > pivote){ j--};           // busca elemento menor que pivote
                   if (i < j) {                        // si no se han cruzado                      
                   aux= A[i];                      // los intercambia
                   A[i]=A[j];
                   A[j]=aux;
                   }
             }
             
             A[izq]=A[j];      // se coloca el pivote en su lugar de forma que tendremos                                    
             A[j]=pivote;      // los menores a su izquierda y los mayores a su derecha
             
             if(izq < j-1){
                quicksort(A,izq,j-1); // ordenamos subarray izquierdo
               }         
             if(j+1 < der){
                quicksort(A,j+1,der); // ordenamos subarray derecho
                }         
             
          }

    // Mergesort
    public static void mergesort(int A[], int izq, int der) {
        if (izq < der) {
            int m = (izq + der) / 2;
            mergesort(A, izq, m);
            mergesort(A, m + 1, der);
            merge(A, izq, m, der);
        }
    }

    public static void merge(int A[], int izq, int m, int der) {
        int i, j, k;
        int[] B = new int[A.length]; // array auxiliar
        for (i = izq; i <= der; i++) { // copia ambas mitades en el array auxiliar
            B[i] = A[i];

            i = izq;
            j = m + 1;
            k = izq;

            while (i <= m && j <= der) {// copia el siguiente elemento más grande
                if (B[i] <= B[j]) {
                    A[k++] = B[i++];
                } else {
                    A[k++] = B[j++];
                }
            }
            while (i <= m) { // copia los elementos que quedan de la
                A[k++] = B[i++]; // primera mitad (si los hay)
            }
        }
    }

}
