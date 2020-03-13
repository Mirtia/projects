import pandas as pd
from pathlib import Path
import matplotlib.pyplot as plt
import matplotlib.ticker as ticker
import tkinter as tk
from tkinter import filedialog
import ntpath as nt
import os
import math
import numpy as np

# Example csv
# Time(s)  | #Vertices | Type

csv_num = 2   #number of csv files to parse
df = None    
file_path = None

colors = ['r' ,'g', 'b','y']

def getCSV ():
    global df
    global file_path
    file_path = filedialog.askopenfilename()
    df = pd.read_csv (file_path)
    root.destroy()


for x in range(0,csv_num):

    
    root = tk.Tk()

    canvas1 = tk.Canvas(root, width = 200, height = 200, bg = 'white', relief = 'raised')
    root.title("Graph")

 
    
    browseButton_CSV = tk.Button(text="IMPORT CSV FILE", command=getCSV, 
    bg='lightblue', fg='white', font=('arial', 12, 'bold'))
    browseButton_CSV.place(relx=0.5, rely=0.5, anchor='center')
    browseButton_CSV.pack()
    canvas1.create_window(0,0)
    root.mainloop()

    time = df['Time(s)'].values
    vertices = df['#Vertices'].values
    labels = df['Type'].values

    ax = plt.gca()

    plt.vlines(time,0,vertices, linestyle="dashed")
    plt.hlines(vertices,0,time, linestyle="dashed")
    plt.scatter(time, vertices, zorder=2)

    df.plot( color=colors[x] ,kind='line',marker='.',x='Time(s)',y='#Vertices', ax=ax,label = labels[0])
    s = [ 0 , 1]
    sint = range(min(s), math.ceil(max(s))+1)


    ax.xaxis.set_minor_locator(ticker.MultipleLocator(0.050))
    ax.xaxis.set_minor_formatter(ticker.FormatStrFormatter('%.3f'))   
    
    plt.xticks(sint)
    
    plt.xlim(0,0.5)
    plt.ylim(0,2000000)
    plt.grid()




print("Title:")
plt.title(input())
plt.show()











