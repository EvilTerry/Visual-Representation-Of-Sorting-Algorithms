import nl.saxion.app.SaxionApp;

import java.awt.*;
import java.lang.reflect.Array;
import java.util.ArrayList;

public class Application implements Runnable {

    public static void main(String[] args) {
        SaxionApp.start(new Application(), 1020, 1000);
    }

    /*

    Currently there are 7 working sorting algorithms
    Bubble Sort
    Bogo Sort
    Odd-Even Sort
    Merge Sort              (Not working)
    Cocktail Shaker Sort
    Gnome Sort
    Insertion Sort
    Pancake Sort

    TO DO :
    Quick Sort
    Heap Sort           (HARD)
    Radix Sort (LSD)    (EXTRA HARD)
    Shell Sort


    */
    String selectedAlgorithm = "Merge Sort";
    int size = 4;
    int speed = 1;
    long start = 0;
    long elapsedTime = 0;
    ArrayList<Bar> array = generateList(size);

    public void run()
    {
        generateList(size);
        boolean isRunning = true;
        while(isRunning)
        {
            displayArray(array);
            switch (printMenu())
            {
                case 1:
                    array = randomizeList(array);
                    break;
                case 2:
                    array = sortArray(array);
                    break;
                case 3:
                    SaxionApp.print("Size: ");
                    size = SaxionApp.readInt();
                    array = generateList(size);
                    break;
                case 4:
                    SaxionApp.print("Speed: ");
                    speed = SaxionApp.readInt();
                    break;
                case 5:
                    selectedAlgorithm = chooseAlgorithm();
                    break;
                case 6:
                    flashArray(array);
                    break;
                case 0:
                    isRunning = false;
                    break;
            }
            SaxionApp.clear();
        }
    }

    public int printMenu()
    {
        SaxionApp.printLine("Select an option: ");
        SaxionApp.printLine();
        SaxionApp.printLine("1. Randomize Array          3. Select size");
        SaxionApp.printLine("2. Sort Array               4. Select speed");
        SaxionApp.printLine("                            5. Select Sort");
        SaxionApp.printLine("                            6. Flash array");
        SaxionApp.printLine();
        SaxionApp.printLine("0. Exit");
        SaxionApp.printLine();
        SaxionApp.print("Selection: ");
        return SaxionApp.readInt();
    }

    public void printParameters()
    {
        for(int i = 0; i < 22; i++)
            SaxionApp.printLine();

        SaxionApp.print("Selected Algorithm: ");
        SaxionApp.printLine(selectedAlgorithm, Color.YELLOW);
        SaxionApp.print("Size: ");
        SaxionApp.printLine(size, Color.YELLOW);
        SaxionApp.print("Speed: ");
        SaxionApp.printLine(speed, Color.YELLOW);
        SaxionApp.printLine();
    }

    public String chooseAlgorithm()
    {
        SaxionApp.clear();
        displayArray(array);

        SaxionApp.printLine("1. Bubble Sort");
        SaxionApp.printLine("2. Bogo Sort");
        SaxionApp.printLine("3. Odd-Even Sort");
        SaxionApp.printLine("4. Merge Sort");
        SaxionApp.printLine("5. Cocktail Shaker Sort");
        SaxionApp.printLine("6. Gnome Sort");
        SaxionApp.printLine("7. Insertion Sort");
        SaxionApp.printLine("8. Pancake Sort");
        SaxionApp.printLine();

        SaxionApp.print("Selection: ");
        int selection = SaxionApp.readInt();

        switch (selection)
        {
            case 1:
                return "Bubble Sort";
            case 2:
                return "Bogo Sort";
            case 3:
                return "Odd-Even Sort";
            case 4:
                return "Merge Sort";
            case 5:
                return "Cocktail Shaker Sort";
            case 6:
                return "Gnome Sort";
            case 7:
                return "Insertion Sort";
            case 8:
                return "Pancake Sort";
        }

        return null;
    }

    public ArrayList<Bar> generateList(int size)
    {
        ArrayList<Bar> list = new ArrayList<>();

        for(int i = size; i > 0; i--)
        {
            Bar newBar = new Bar(i);
            list.add(newBar);
        }

        return list;
    }

    public ArrayList<Bar> randomizeList(ArrayList<Bar> list)
    {
        if(list.isEmpty())
            return null;

        for (Bar bar : list)
            bar.setColor(Color.RED);


        for(int i = 0; i < list.size() * 10; i++)
        {
            int index1 = SaxionApp.getRandomValueBetween(0, list.size());
            int index2 = SaxionApp.getRandomValueBetween(0, list.size());

            Bar valueAtIndex1 = list.get(index1);
            Bar valueAtIndex2 = list.get(index2);

            list.set(index2, valueAtIndex1);
            list.set(index1, valueAtIndex2);
        }

        return list;
    }

    // Displays arrays and also the parameters (currentAlgorithm, size, speed)
    public void displayArray(ArrayList<Bar> list)
    {
        SaxionApp.clear();
        if(list.isEmpty())
            return;


        if(start != 0)
        {
            elapsedTime = System.currentTimeMillis() - start;
        }

        SaxionApp.print("Current elapsed time: " + elapsedTime + " ms");

        SaxionApp.setBorderSize(1);
        SaxionApp.setBorderColor(Color.BLACK);

        if(list.size() >= 300)
            SaxionApp.turnBorderOff();
        else
            SaxionApp.turnBorderOn();

        for(int i = 0; i < list.size(); i++)
        {
            double ratio = (double)200 / list.size();
            int number = list.get(i).getValue() * 2;

            double width = (double)1000 / list.size();
            int height = (int)(number * ratio) + 5;
            double x = width * i + 10;
            int y = 200 * 2 - height + 40;

            SaxionApp.setFill(list.get(i).getColor());

            SaxionApp.drawRectangle((int)x,y,(int)width, height);
        }

        printParameters();
    }


    // Method to call arrays
    public ArrayList<Bar> sortArray(ArrayList<Bar> list)
    {
        ArrayList<Bar> sortedArray = new ArrayList<>();

        start = System.currentTimeMillis();
        switch(selectedAlgorithm)
        {
            case "Bubble Sort":
                sortedArray = bubbleSort(list);
                break;
            case "Bogo Sort":
                sortedArray = bogoSort(list);
                break;
            case "Odd-Even Sort":
                sortedArray = oddEvenSort(list);
                break;
            case "Merge Sort":
                sortedArray = mergeSort2(list);
                break;
            case "Cocktail Shaker Sort":
                sortedArray = cocktailShakerSort(list);
                break;
            case "Gnome Sort":
                sortedArray = gnomeSort(list);
                break;
            case "Insertion Sort":
                sortedArray = insertionSort(list);
                break;
            case "Pancake Sort":
                sortedArray = pancakeSort(list);
                break;
        }
        start = 0;
        flashArray(sortedArray);

        return sortedArray;
    }

    // Flashes array when sorting is done
    public void flashArray(ArrayList<Bar> list)
    {
        if(list.isEmpty())
            return;

        for(int i = 0; i < list.size(); i++)
        {
            list.get(i).setColor(Color.YELLOW);
            displayArray(list);
            SaxionApp.sleep(0.008 * 100 / list.size());

            list.get(i).setColor(Color.GREEN);
            displayArray(list);
        }

        for(int i = list.size() - 1; i >= 0; i--)
        {
            list.get(i).setColor(Color.YELLOW);
            displayArray(list);
            SaxionApp.sleep(0.008 * 100 / list.size());

            list.get(i).setColor(Color.GREEN);
            displayArray(list);
        }
    }
    public ArrayList<Bar> turnArrayRed(ArrayList<Bar> list)
    {
        for(int i = 0; i < list.size(); i++)
            list.get(i).setColor(Color.RED);

        return list;
    }
    public Color getRandomColor()
    {
        int red = SaxionApp.getRandomValueBetween(0, 256);
        int green = SaxionApp.getRandomValueBetween(0, 256);
        int blue = SaxionApp.getRandomValueBetween(0, 256);

        return SaxionApp.createColor(red, green, blue);
    }

    /*

    SORTING ALGORITHMS

    */

    public ArrayList<Bar> swap(ArrayList<Bar> list, int i, int j)
    {
        Bar temp = list.get(i);
        list.set(i, list.get(j));
        list.set(j, temp);

        return list;
    }
    public ArrayList<Bar> bubbleSort(ArrayList<Bar> list)
    {
        for(int i = 0; i < list.size(); i++)
        {
            for(int j = i + 1; j < list.size(); j++)
            {
                list.get(i).setColor(Color.YELLOW);
                list.get(j).setColor(Color.YELLOW);

                if(list.get(i).getValue() < list.get(j).getValue())
                    list = swap(list, i, j);

                displayArray(list);
                SaxionApp.sleep(0.5 / speed);

                list.get(i).setColor(Color.RED);
                list.get(j).setColor(Color.RED);
            }
            list.get(i).setColor(Color.GREEN);
            displayArray(list);
            SaxionApp.sleep(0.5 / speed);
        }

        return list;
    }
    public ArrayList<Bar> bogoSort(ArrayList<Bar> list)
    {
        while(true)
        {
            boolean isSorted = true;
            for(int i = 0; i < list.size() - 1; i++)
            {
                list.get(i).setColor(Color.YELLOW);
                list.get(i + 1).setColor(Color.YELLOW);

                if(list.get(i).getValue() < list.get(i + 1).getValue())
                {
                    displayArray(list);
                    SaxionApp.sleep(0.5 / speed);
                    isSorted = false;
                    break;
                }

                displayArray(list);
                SaxionApp.sleep(0.5 / speed);

                list.get(i).setColor(Color.RED);
                list.get(i + 1).setColor(Color.RED);

                list.get(i).setColor(Color.GREEN);
                SaxionApp.sleep(0.5 / speed);
            }
            if(isSorted)
            {
                list.get(array.size() - 1).setColor(Color.GREEN);
                break;
            }


            randomizeList(list);
            displayArray(list);
            SaxionApp.sleep(0.5 / speed);
        }

        return list;
    }
    public ArrayList<Bar> oddEvenSort(ArrayList<Bar> list)
    {

        boolean sorted = false;
        while(!sorted)
        {
            sorted = true;
            for(int i = 1; i < list.size() - 1; i += 2)
            {

                list.get(i).setColor(Color.YELLOW);
                list.get(i + 1).setColor(Color.YELLOW);
                if(list.get(i).getValue() < list.get(i + 1).getValue())
                {
                    list = swap(list, i, i + 1);
                    sorted = false;
                }

                displayArray(list);
                SaxionApp.sleep(0.5 / speed);

                list.get(i).setColor(Color.RED);
                list.get(i + 1).setColor(Color.RED);
            }
            for(int i = 0; i < list.size() - 1; i += 2)
            {
                list.get(i).setColor(Color.YELLOW);
                list.get(i + 1).setColor(Color.YELLOW);
                if(list.get(i).getValue() < list.get(i + 1).getValue())
                {
                    list = swap(list, i, i + 1);
                    sorted = false;
                }

                displayArray(list);
                SaxionApp.sleep(0.5 / speed);

                list.get(i).setColor(Color.RED);
                list.get(i + 1).setColor(Color.RED);
            }
        }
        return list;
    }

    // MERGE SORT START
    public ArrayList<Bar> mergeSort(ArrayList<Bar> list)
    {
        ArrayList<Bar> sortedArray = new ArrayList<>();

        int[] arrayIndex = new int[list.size()];

        for(int i = 0; i < list.size(); i++)
            arrayIndex[i] = i;

        arrayIndex = mergeSortIndex(arrayIndex, list);

        for(int i = 0; i < list.size(); i++)
        {
            sortedArray.add(list.get(arrayIndex[i]));
        }

        return sortedArray;
    }

    public int[] mergeSortIndex(int[] list, ArrayList<Bar> aList)
    {
        ArrayList<Bar> arrayList = new ArrayList<>(aList);

        if(list.length <= 1)
            return list;

        int[] left = new int[list.length / 2];
        Color leftColor = getRandomColor();

        int[] right = new int[list.length - left.length];
        Color rightColor = getRandomColor();

        int leftIndex = 0;
        int rightIndex = 0;

        for(int i = 0; i < list.length; i++)
        {
            if(i < list.length / 2)
            {
                left[leftIndex] = list[i];
                leftIndex++;
                arrayList.get(list[i]).setColor(leftColor);
            }
            else
            {
                right[rightIndex] = list[i];
                rightIndex++;
                arrayList.get(list[i]).setColor(rightColor);
            }

            displayArray(arrayList);
            SaxionApp.sleep(0.5 / speed);
        }

        left = mergeSortIndex(left, arrayList);
        right = mergeSortIndex(right, arrayList);

        for(int i = 0; i < right.length; i++)
        {
            arrayList.get(right[i]).setColor(rightColor);
        }
        for(int i = 0; i < left.length; i++)
        {
            arrayList.get(left[i]).setColor(rightColor);
        }


        return mergeIndex(left, right, arrayList);
    }

    public int[] mergeIndex(int[] left, int[] right, ArrayList<Bar> arrayList)
    {
        int[] mergedArray = new int[left.length + right.length];

        int leftIndex = 0;
        int rightIndex = 0;
        int mergedIndex = 0;

        while(leftIndex < left.length && rightIndex < right.length)
        {
            if(array.get(left[leftIndex]).getValue() >= array.get(right[rightIndex]).getValue())
            {
                mergedArray[mergedIndex] = left[leftIndex];
                leftIndex++;
            }
            else
            {
                mergedArray[mergedIndex] = right[rightIndex];
                rightIndex++;
            }

            mergedIndex++;
        }

        while(leftIndex < left.length)
        {
            mergedArray[mergedIndex] = left[leftIndex];

            leftIndex++;
            mergedIndex++;
        }

        while(rightIndex < right.length)
        {
            mergedArray[mergedIndex] = right[rightIndex];

            rightIndex++;
            mergedIndex++;
        }

        return mergedArray;
    }

    public ArrayList<Bar> mergeSort2(ArrayList<Bar> list)
    {
        if(list.size() <= 1)
            return list;

        ArrayList<Bar> left = new ArrayList<>();
        ArrayList<Bar> right = new ArrayList<>();

        Color leftColor = getRandomColor();
        Color rightColor = getRandomColor();


        for(int i = 0; i < list.size(); i++)
        {
            if(i < list.size() / 2)
            {
                left.add(list.get(i));
                list.get(i).setColor(leftColor);
            }
            else
            {
                right.add(list.get(i));
                list.get(i).setColor(rightColor);
            }
        }
        displayArray(list);
        SaxionApp.pause();

        int indexBegin = list.indexOf(left.get(0));
        int indexEnd = list.indexOf(right.get(right.size() - 1));


        left = mergeSort2(left);
        right = mergeSort2(right);

        for(int i = 0; i < right.size(); i++)
        {
            list.get(list.indexOf(right.get(i))).setColor(rightColor);
        }

        for(int i = 0; i < left.size(); i++)
        {
            list.get(list.indexOf(left.get(i))).setColor(leftColor);
        }

        displayArray(list);
        SaxionApp.pause();

        return merge2(left, right, indexBegin, indexEnd, list);
    }


    public ArrayList<Bar> merge2(ArrayList<Bar> left, ArrayList<Bar> right, int indexBegin, int indexEnd, ArrayList<Bar> list)
    {
        ArrayList<Bar> result = new ArrayList<>();

        int leftIndex = 0;
        int rightIndex = 0;

        while(leftIndex < left.size() && rightIndex < right.size())
        {
            if(left.get(leftIndex).getValue() >= right.get(rightIndex).getValue())
            {
                result.add(left.get(leftIndex));
                leftIndex++;
            }
            else
            {
                result.add(right.get(rightIndex));
                rightIndex++;
            }
        }

        while(leftIndex < left.size())
        {
            result.add(left.get(leftIndex));
            leftIndex++;
        }

        while(rightIndex < right.size())
        {
            result.add(right.get(rightIndex));
            rightIndex++;
        }

        reconstructArray(list, result, indexBegin, indexEnd);

        return result;
    }

    public void reconstructArray(ArrayList<Bar> list, ArrayList<Bar> sortedList, int indexBegin, int indexEnd)
    {
        int sortedIndex = 0;

        for(int i = indexBegin; i <= indexEnd; i++)
        {
            list.set(i, sortedList.get(sortedIndex));
            sortedIndex++;
        }

        displayArray(list);
        SaxionApp.pause();
    }

    // MERGE SORT END
    public ArrayList<Bar> cocktailShakerSort(ArrayList<Bar> list)
    {
        int beginIndex = 0;
        int endIndex = list.size() - 1;
        boolean swapped = false;

        while(true)
        {
            for(int i = beginIndex; i < endIndex; i++)
            {
                list.get(i).setColor(Color.YELLOW);
                displayArray(list);
                SaxionApp.sleep(0.5 / speed);
                list.get(i).setColor(Color.RED);
                if(list.get(i).getValue() < list.get(i + 1).getValue())
                {
                    list = swap(list, i, i + 1);
                    swapped = true;
                }
            }
            list.get(endIndex).setColor(Color.GREEN);
            endIndex--;

            if(!swapped)
                break;

            for(int i = endIndex; i > beginIndex; i--)
            {
                list.get(i).setColor(Color.YELLOW);
                displayArray(list);
                SaxionApp.sleep(0.5 / speed);
                list.get(i).setColor(Color.RED);
                if(list.get(i).getValue() > list.get(i - 1).getValue())
                {
                    list = swap(list, i, i - 1);
                    swapped = true;
                }
            }
            list.get(beginIndex).setColor(Color.GREEN);
            beginIndex++;

            if(!swapped)
                break;

            swapped = false;
        }

        return list;
    }
    public ArrayList<Bar> gnomeSort(ArrayList<Bar> list)
    {
        int pos = 0;
        while(pos < list.size())
        {
            list.get(pos).setColor(Color.YELLOW);
            displayArray(list);
            SaxionApp.sleep(0.5 / speed);
            list.get(pos).setColor(Color.RED);
            if(pos == 0 || list.get(pos).getValue() <= list.get(pos - 1).getValue())
            {
                pos++;
            }
            else
            {
                list = swap(list, pos, pos - 1);
                pos--;
            }
        }
        return list;
    }
    public ArrayList<Bar> insertionSort(ArrayList<Bar> list)
    {
        int i = 1;
        while(i < list.size())
        {
            int j = i;
            while(j > 0 && list.get(j - 1).getValue() < list.get(j).getValue())
            {
                list.get(j).setColor(Color.YELLOW);
                displayArray(list);
                SaxionApp.sleep(0.5 / speed);
                list.get(j).setColor(Color.RED);
                list = swap(list, j - 1, j);
                j--;
            }
            i++;
        }

        return list;
    }
    public ArrayList<Bar> pancakeSort(ArrayList<Bar> list)
    {
        int n = list.size();
        while(n > 1)
        {
            int minIndex = pancakeSmallestIndex(list, n);
            if(minIndex != n - 1)
            {
                if(minIndex != 0)
                {
                    list = pancakeFlip(list, minIndex);
                }
                list = pancakeFlip(list, n - 1);
            }
            n--;
        }

        return list;
    }
    public ArrayList<Bar> pancakeFlip(ArrayList<Bar> list, int k)
    {
        int left = 0;
        int tempK = k;

        displayArray(list);
        SaxionApp.sleep(0.5 / speed);

        while(left < tempK)
        {
            list.get(tempK).setColor(Color.YELLOW);
            list.get(left).setColor(Color.YELLOW);
            tempK--;
            left++;
        }

        displayArray(list);
        SaxionApp.sleep(0.5 / speed);

        left = 0;

        while(left < k)
        {
            list = swap(list, left, k);
            k--;
            left++;
        }

        displayArray(list);
        SaxionApp.sleep(0.5 / speed);

        list = turnArrayRed(list);

        return list;
    }
    public int pancakeSmallestIndex(ArrayList<Bar> list, int k)
    {
        int index = 0;

        for(int i = 0; i < k; i++)
            if(list.get(i).getValue() < list.get(index).getValue())
                index = i;

        return index;
    }
}