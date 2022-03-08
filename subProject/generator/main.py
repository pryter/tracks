import random
import string
import urls
import template
from pytube import YouTube
from pydub import AudioSegment
import os
import disc

songs = raw.split("\n")

def id_generator(size=6, chars=string.ascii_lowercase + string.digits):
        return ''.join(random.choice(chars) for _ in range(size))

def download(url):
    yt = YouTube(url)
    stream = yt.streams.last()
    stream.download(".temp")

def convert(input, filename):
    AudioSegment.from_file(f'{input}', format="webm").export(f'dist/sounds/{filename}.ogg', format='ogg')

def generate(inner, templateFac, fname):
    data = templateFac("".join(inner))
    f = open(f'dist/scripts/{fname}', "w")
    f.write(data)
    f.close()

items = []
events = []
langs = []
sounds = []
i = 0

for url in songs:
    i += 1
    id = id_generator(8)
    discId = f'disc_{id}'
    trackId = f'track_{id}'
    title = "Undefined"

    print(f'{i}/{len(songs)} Working on {url}')
    download(url)
    for file in os.listdir(".temp"):
        if file.endswith(".webm"):
            title = file.replace(".webm", "")
            tempfname = os.path.join(".temp", file)
            convert(tempfname, id)
            os.remove(tempfname)
        else:
            print("Error")
    
    items.append(template.createSapItem(discId, trackId))
    events.append(template.createSapEvent(trackId))
    langs.append(template.createSapLang(discId, title))
    f = open(f"dist/models/{discId}.json", "w")
    f.write(template.createModel(discId))
    f.close()
    disc.doGenDisk(discId)
    sounds.append(template.createSapSound(trackId, id))

generate(items, template.createItem, "Items.java")
generate(events, template.createEvent, "Events.java")
generate(langs, template.createLang, "en_us.json")
generate(sounds, template.createSound, "sounds.json")

    

    

    



