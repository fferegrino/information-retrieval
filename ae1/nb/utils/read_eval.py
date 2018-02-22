import numpy as np
import pandas as pd
from pandas.core.series import Series
from pandas.core.frame import DataFrame


def read_eval(eval_file, skip_first=True):
    data = pd.read_table(eval_file,
                         sep='\t',
                         header=None,
                         skiprows=[0] if skip_first else None,
                         names=['measure', 'query', 'value'])
    # Split frames:
    data.dropna(inplace=True)
    data['measure'] = data['measure'].str.strip()
    
    per_query = data[data['query'] != 'all'].pivot(index='query', columns='measure', values='value')
    per_query.index = per_query.index.astype(int)
    per_query.sort_index(inplace=True)
    
    totals = data[data['query'] == 'all'].pivot(index='query', columns='measure', values='value')
    
    return totals, per_query


def get_recalls(data):
    if type(data) == DataFrame:
        return _get_recalls_from_dataframe(data)
    elif type(data) == Series:
        return _get_recalls_from_series(data)
    return None

def _get_recalls_from_series(data):
    recalls = data.loc['iprec_at_recall_0.00':'iprec_at_recall_1.00'].to_frame().reset_index()
    values = [f for f in np.linspace(0, 1, 11)]
    recalls.columns = ['recall', 'value']
    recalls.recall = values
    recalls.value = recalls.value.astype(float)
    return recalls

def _get_recalls_from_dataframe(data):
    recall_at = data.loc[:, 'iprec_at_recall_0.00':'iprec_at_recall_1.00']
    column_names = ["%.2f" % f for f in np.linspace(0, 1, 11)]
    recall_at.columns = column_names
    recall_at.reset_index(inplace=True)
    recalls = pd.melt(recall_at, id_vars=['query'], value_vars=column_names, var_name='recall')
    recalls.recall = recalls.recall.astype(float)
    recalls.value = recalls.value.astype(float)
    return recalls

def merge_same_topic(frames, names):
    for f,n in zip(frames, names):
        f['model'] = n
    return pd.concat(frames)