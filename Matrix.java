public class Matrix {
	private double[][] data;
	
	public Matrix(String data){
		String temp = "";
		int rows = Integer.parseInt(data.charAt(0)+"");
		int columns = Integer.parseInt(data.charAt(2)+"");
		double[][] matrix = new double[rows][];
		data = data.substring(4);
		
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
	public double[] getRow(int index){
		return data[index];
	}
	public double[] getColumn(int index){
		double[] column = new double[getNumRows()];
		for (int i = 0; i < getNumRows(); i++){
			column[i] = data[i][index];
		}
		return column;
	}
	public void transposeMatrix(){
		double[][] newData = new double[getNumColumns()][];
		for (int i = 0; i < getNumColumns(); i++){
			newData[i] = getColumn(i);
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
