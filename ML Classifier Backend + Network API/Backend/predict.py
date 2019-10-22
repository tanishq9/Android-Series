import numpy as np
import pandas as pd
import pickle
import sys
from sklearn.ensemble import RandomForestClassifier

def predict(preg,glucose,bp,st,insulin,bmi,dbf,age):
    input = [[preg,glucose,bp,st,insulin,bmi,dbf,age]]
    model=pickle.load(open('model_pima_diabetes.pkl','rb'))
    print(model.predict(input)[0])    
    
if __name__ == "__main__":
    # sys.argv[0] is the name of the script i.e predict.py
    preg=float(sys.argv[1])
    glucose=float(sys.argv[2])
    bp=float(sys.argv[3])
    st=float(sys.argv[4])
    insulin=float(sys.argv[5])
    bmi=float(sys.argv[6])
    dbf=float(sys.argv[7])
    age=float(sys.argv[8])
    predict(preg,glucose,bp,st,insulin,bmi,dbf,age)

    