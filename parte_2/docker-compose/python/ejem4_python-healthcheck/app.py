from flask import Flask, render_template
from pymongo import MongoClient
import random
import socket
import os

app = Flask(__name__)

# Database connection
dbHost = "localhost" if os.getenv("MONGODB_HOST") is None else os.getenv("MONGODB_HOST")
dbPort = "27017" if os.getenv("MONGODB_PORT") is None else os.getenv("MONGODB_PORT")

client = MongoClient(dbHost, int(dbPort))
db = client.ipsdb

# list of cat images
images = [
    "https://media.giphy.com/media/mlvseq9yvZhba/giphy.gif",
    "https://media.giphy.com/media/MCfhrrNN1goH6/giphy.gif",
    "https://media.giphy.com/media/8vQSQ3cNXuDGo/giphy.gif",
    "https://media.giphy.com/media/rwCX06Y5XpbLG/giphy.gif",
    "https://media.giphy.com/media/vFKqnCdLPNOKc/giphy.gif",
    "https://media.giphy.com/media/CqVNwrLt9KEDK/giphy.gif",
    "https://media.giphy.com/media/MWSRkVoNaC30A/giphy.gif",
    "https://media.giphy.com/media/TEkr9oBZ57KFmMWScZ/giphy.gif",
]


@app.route("/")
def index():
    url = random.choice(images)
    s = socket.socket(socket.AF_INET, socket.SOCK_DGRAM)
    s.connect(("8.8.8.8", 80))
    ip = s.getsockname()[0]
    s.close()

    _ips = db.ips.find()
    ips = [ip for ip in _ips]

    db.ips.insert_one({"ip": ip})

    return render_template("index.html", url=url, ip=ip, ips=ips)


if __name__ == "__main__":
    app.run(host="0.0.0.0")
