public class Matrix {
    private double[][] data;
    
    public Matrix(String data){
        String temp = "";
        String[] ints = data.split(" ");
        int rows = Integer.parseInt(ints[0]);
        int rowsLength = String.valueOf(rows).length();
        int columns = Integer.parseInt(ints[1]);
        int columnsLength = String.valueOf(columns).length();
        double[][] matrix = new double[rows][];
        data = data.substring(rowsLength+columnsLength+2);
        
        
        int newRow = 0;
        int rowIndex = 0;
        for(int i = 0; i < matrix.length; i++){
            matrix[i] = new double[columns];
        }
        int numbers = 0;
        int i = 0;
        while(numbers < rows * columns){
            if (i == data.length() || data.charAt(i) == ' '){
                matrix[rowIndex][newRow] = Double.parseDouble(temp);
                temp = "";
                newRow++;
                if (newRow == columns){
                    newRow = 0;
                    rowIndex++;
                }
                numbers++;
            }
            else{
                temp = temp + data.charAt(i);
            }
            i++;
        }
        this.data = matrix;
    }
    
    public Matrix(int rows, int columns){
        this.data = new double[rows][];
        for (int i = 0 ; i < rows ; i++){
            this.data[i] = new double[columns];
        }
    }
    public double getAtPosition(int row, int column){
        return data[row][column];
    }
    
    public void insertAtPosition(int row, int column, double value){
        data[row][column] = value;
    }
    
    public int getNumRows(){
        return data.length;
    }
    public int getNumColumns(){
        return data[0].length;
    }
    public Matrix getRow(int index){
        Matrix row = new Matrix(1, getNumColumns());
        for (int i = 0; i < getNumColumns(); i++){
            row.insertAtPosition(0, i, data[index][i]);
        }
        return row;
    }
    public Matrix getColumn(int index){
        Matrix column = new Matrix(getNumRows(), 1);
        for (int i = 0; i < getNumRows(); i++){
            column.insertAtPosition(i, 0, data[i][index]);
        }
        return column;
    }
    private double[] getRowArray(int index){
        return data[index];
    }
    private double[] getColumnArray(int index){
        double[] column = new double[getNumRows()];
        for (int i = 0; i < getNumRows(); i++){
            column[i] = data[i][index];
        }
        return column;
    }
    public void transposeMatrix(){
        double[][] newData = new double[getNumColumns()][];
        for (int i = 0; i < getNumColumns(); i++){
            newData[i] = getColumnArray(i);
        }
        this.data = newData;
    }
    
    public Matrix multilpyLeftOf(Matrix matrix){
        return multiplyMatrix(this, matrix);
    }
    
    public Matrix multilpyRightOf(Matrix matrix){
        return multiplyMatrix(matrix, this);
    }
    
    private Matrix multiplyMatrix(Matrix left, Matrix right){
        if(left.getNumColumns() != right.getNumRows()){
            throw new NumberFormatException("Columnspace does not match rowspace");
        }
        Matrix product = new Matrix(left.getNumRows(), right.getNumColumns());
        for (int i = 0 ; i < left.getNumRows() ; i++){
            for (int j = 0 ; j < right.getNumColumns() ; j++){
                double value = 0;
                for(int k = 0 ; k < left.getNumColumns() ; k++){
                    value = value + left.getAtPosition(i, k) * right.getAtPosition(k, j);
                }
                product.insertAtPosition(i, j, value);
            }
        }
        return product;
    }
    
    public Matrix elementWiseMultiplication(Matrix matrix){
        if (getNumColumns() != 1 || matrix.getNumColumns() != 1){
            throw new NumberFormatException("More than one column");
        }
        else if(getNumRows() != matrix.getNumRows()){
            throw new NumberFormatException("Different number of rows");
        }
        else{
            Matrix product = new Matrix(getNumRows(), 1);
            for (int i = 0; i < getNumRows(); i++){
                product.insertAtPosition(i, 0, getAtPosition(i,0) * matrix.getAtPosition(i,0));
            }
            return product;
        }
    }
    
    public double RowsSum() {
        double result = 0;
        if (this.getNumColumns() > 1) {
            throw new NumberFormatException("Too many columns to sum");
        }
        for (int i = 0; i < this.getNumRows(); i++) {
            result += this.getAtPosition(i, 0);
        }
        return result;
    }
    
    public String toString(){
        String matrixString = "{";
        for(int i = 0; i < getNumRows(); i++){
            for (int j = 0; j < getNumColumns(); j++){
                if (j != getNumColumns() - 1){
                    matrixString = matrixString + data[i][j] + ", ";
                }
                else{
                    matrixString = matrixString + data[i][j];
                }
            }
            if (i != getNumRows() - 1){
                matrixString = matrixString + "\n";
            }
            else {
                matrixString = matrixString + "}";
            }
        }
        return matrixString + "\n";
    }
    
    public String toKattisString(){
        String matrixString = getNumColumns() + " " + getNumRows() + " ";
        for(int i = 0; i < getNumRows(); i++){
            for (int j = 0; j < getNumColumns(); j++){
                if (i == getNumRows() - 1 && j == getNumColumns() - 1){
                    matrixString = matrixString + data[i][j];
                }
                else{
                    matrixString = matrixString + data[i][j] + " ";
                }
            }
        }
        return matrixString;
    }
}
