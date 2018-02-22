import pandas as pd
import numpy as np
import matplotlib.pyplot as plt

from utils.read_eval import get_recalls

def plot_recall_at(total, pq, position, model_name=None):    
    total_recall = get_recalls(total)
    plt.subplot(2, 2, position)
    plt.plot(total_recall.recall, total_recall.value, marker ='.', label='MAP')
    if model_name is not None:
        plt.title(model_name)
    plt.ylabel('Precission')
    plt.ylim([-0.02,1.02])
    plt.xlabel('Recall')
    plt.xlim([-0.02,1.02])
    plt.legend(loc='upper right')
    plt.margins(0.2)
    for ix, query_detail in pq.iterrows():
        qq = get_recalls(query_detail)
        plt.step(qq.recall, qq.value, alpha=0.2, label=None)


def plot_comparative_recall_at(one, two, one_title, two_title, plot_title=None):
    titles = [one_title, two_title]
    frames = [one, two]
    plt.figure(figsize=(20,10))
    if plot_title is not None:
        plt.suptitle(plot_title)
    for i,frame in enumerate(frames):
        avg_recall = get_recalls(frame.mean())
        plt.subplot(1, 2, i + 1)
        plt.title(titles[i])
        plt.plot(avg_recall.recall, avg_recall.value, marker ='.', label='MAP')
        plt.ylabel('Precission')
        plt.ylim([-0.02,1.02])
        plt.xlabel('Recall')
        plt.xlim([-0.02,1.02])
        plt.legend(loc='upper right')
        plt.margins(0.2)
        for ix, query_detail in frame.iterrows():
            qq = get_recalls(query_detail)
            plt.step(qq.recall, qq.value, alpha=0.2, label=None)