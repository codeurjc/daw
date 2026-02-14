import os
import pandas as pd
import matplotlib.pyplot as plt

if __name__ == "__main__":
    # Lectura variables de entorno
    TOP_TEAMS_TO_DISPLAY = int(os.getenv("TOP_TEAMS_TO_DISPLAY", 5))
    TOP_SCORERS_TO_DISPLAY = int(os.getenv("TOP_SCORERS_TO_DISPLAY", 5))
    MINUTE_SEGMENTS = int(os.getenv("MINUTE_SEGMENTS", 15))
    OUTPUT_DIR = os.getenv("OUTPUT_DIR", "output")
    GOALS_BY_TEAM_FILE_NAME = os.getenv("GOALS_BY_TEAM_FILE_NAME", "goals_by_team.csv")
    TOP_SCORERS_FILE_NAME = os.getenv("TOP_SCORERS_FILE_NAME", "top_scorers.csv")
    MINUTE_SEGMENTS_FILE_NAME = os.getenv("MINUTE_SEGMENTS_FILE_NAME", "goals_by_minute.png")

    print ("Variables de entorno:")
    print ("\t- TOP_TEAMS_TO_DISPLAY: ", TOP_TEAMS_TO_DISPLAY)
    print ("\t- TOP_SCORERS_TO_DISPLAY: ", TOP_SCORERS_TO_DISPLAY)
    print ("\t- MINUTE_SEGMENTS: ", MINUTE_SEGMENTS)
    print ("\t- OUTPUT_DIR: ", OUTPUT_DIR)
    print ("\t- GOALS_BY_TEAM_FILE_NAME: ", GOALS_BY_TEAM_FILE_NAME)
    print ("\t- TOP_SCORERS_FILE_NAME: ", TOP_SCORERS_FILE_NAME)
    print ("\t- MINUTE_SEGMENTS_FILE_NAME: ", MINUTE_SEGMENTS_FILE_NAME)

    # Crear carpeta de salida si no existe
    os.makedirs(OUTPUT_DIR, exist_ok=True)

    # Carga inicial
    scorers_df = pd.read_csv("goalscorers.csv")

    # Goles por equipo

    # Agrupamos por equipo y contamos cuántos goles han marcado,
    # filtrando previamente los goles en propia puerta para no alterar el dato real de goles a favor
    goals_by_team = (
        scorers_df.loc[scorers_df["own_goal"].isna()]
        .groupby("team")
        .size()
        .reset_index(name="goals")
        .sort_values("goals", ascending=False)
    )

    # Guardar en un fichero los 5 primeros
    goals_by_team.head(TOP_TEAMS_TO_DISPLAY).to_csv(OUTPUT_DIR + "/" + GOALS_BY_TEAM_FILE_NAME, index=False)

    # Top goleadores
    # Agrupamos por jugador y contamos cuántos goles han marcado,
    # filtrando previamente los goles en propia puerta para no alterar el dato real de goles a favor
    top_scorers = (
        scorers_df
            .loc[scorers_df["own_goal"].isna()]
            .groupby("scorer")
            .size()
            .reset_index(name="goals")
            .sort_values("goals", ascending=False)
    )

    # Guardar en un fichero los 5 primeros
    top_scorers.head(TOP_SCORERS_TO_DISPLAY).to_csv(OUTPUT_DIR + "/" + TOP_SCORERS_FILE_NAME, index=False)

    # Distribución de goles por tramos de partidos

    # Crear tramos de minutos
    max_minute = int(scorers_df["minute"].max())

    n_bins = max_minute // 15 
    bins = [i * 15 for i in range(n_bins + 1)]
    bins.append(max_minute)

    labels = [
        f"{bins[i] + 1}+"
        if i == len(bins) - 2
        else f"{bins[i] + 1}-{bins[i + 1]}"
        for i in range(len(bins) - 1)
    ]

    scorers_df["minute_bin"] = pd.cut(
        scorers_df["minute"],
        bins=bins,
        labels=labels,
        right=True,
        include_lowest=True
    )

    # Contar goles por tramo (excluyendo propios en este dataset own_goal es NaN cuando NO lo es)
    goals_by_bin = (
        scorers_df
        .loc[scorers_df["own_goal"].isna()]
        .groupby("minute_bin", observed=True)
        .size()
    )

    # Pintar gráfico de barras
    plt.figure(figsize=(8, 5))
    goals_by_bin.plot(kind="bar")

    plt.xlabel("Tramo de minuto")
    plt.ylabel("Número de goles")
    plt.title("Distribución de goles por tramos de partido")
    plt.xticks(rotation=0)
    plt.tight_layout()

    # Guardar el gráfico como PNG
    plt.savefig(OUTPUT_DIR + "/" + MINUTE_SEGMENTS_FILE_NAME, dpi=300, bbox_inches="tight")

    # Salida final
    print("Análisis completado. Resultados almacenados.")