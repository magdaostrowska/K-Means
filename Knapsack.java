public class Knapsack {
    int datasetId;
    int[] sizes;
    int[] values;

    public Knapsack(int datasetId, int[] sizes, int[] values){
        this.datasetId = datasetId;
        this.sizes = sizes;
        this.values = values;
    }

    public void setDatasetId(int datasetId) {
        datasetId = datasetId;
    }

    public void setSizes(int[] sizes) {
        this.sizes = sizes;
    }

    public void setValues(int[] values) {
        this.values = values;
    }

    public int getDatasetId(){
        return datasetId;
    }
    public int[] getSizes(){
        return sizes;
    }
    public int[] getValues(){
        return values;
    }

    @Override
    public String toString() {
        String siz = "";
        String val = "";

        for(int x:sizes){
            siz += x + ", ";
        }

        for(int x:values){
            val += x + ", ";
        }

        return "Values: " + val + "\nSizes: " + siz;
    }
}