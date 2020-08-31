# Distance Measurement
Java application for measure the distance comparison on the K-Nearest Neighbor algorithm for classification

## Paper Documentation
https://www.researchgate.net/publication/341393947_Comparison_of_Distance_Measurement_Methods_on_K-Nearest_Neighbor_Algorithm_For_Classification

## Explenation
Dataset used in this study is cervical cancer dataset that
can be accessed on UCI Machining Learning Repository
[9]. In this study, we developed a software to do
classification process. Testing is done by using cervical
cancer dataset which has 32 predictor attributes and 4
target labels (Hinselmann, Schiller, Cytology dan Biopsy)
and has 858 data objects stored in file with extension .csv.
The process that carried out in this study is data
processing and classification process. At pre-processing
stage, data cleaning is performed which is checking
missing values and outliers data. Missing values were
filled using the sample mean[10], for numerical data is
filled by the average value of each attributes data and for
nominal data is filled by dominant value, while for the
data out of range or called outlier data is removed
manually[11]. Then split the data by cutting from the
system with a comparison of 80% training data and 20%
testing data.