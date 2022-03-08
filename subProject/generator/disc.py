import os
import random
import struct
from sys import hexversion
from PIL import Image

def genRandomColor(offset=0):
    r = lambda: random.randint(0 + offset,255 - offset)
    return '#%02X%02X%02X' % (r(),r(),r())

def color_variant(hex_color, brightness_offset=1):
    """ takes a color like #87c95f and produces a lighter or darker variant """
    if len(hex_color) != 7:
        raise Exception("Passed %s into color_variant(), needs to be in #87c95f format." % hex_color)
    rgb_hex = [hex_color[x:x+2] for x in [1, 3, 5]]
    new_rgb_int = [int(hex_value, 16) + brightness_offset for hex_value in rgb_hex]
    new_rgb_int = [min([255, max([0, i])]) for i in new_rgb_int] # make sure new values are between 0 and 255
    # hex() produces "0x88", we want just "88"
    return "#" + "".join([hex(i)[2:] for i in new_rgb_int])

def changePNGColor(srcimg, fromRgb, toRgb, deltaRank = 10):
    fromRgb = fromRgb.replace('#', '')
    toRgb = toRgb.replace('#', '')

    fromColor = struct.unpack('BBB', bytes.fromhex(fromRgb))
    toColor = struct.unpack('BBB', bytes.fromhex(toRgb))

    img = srcimg
    img = img.convert("RGBA")
    pixdata = img.load()

    for x in range(0, img.size[0]):
        for y in range(0, img.size[1]):
            rdelta = pixdata[x, y][0] - fromColor[0]
            gdelta = pixdata[x, y][0] - fromColor[0]
            bdelta = pixdata[x, y][0] - fromColor[0]
            if abs(rdelta) <= deltaRank and abs(gdelta) <= deltaRank and abs(bdelta) <= deltaRank:
                pixdata[x, y] = (toColor[0] + rdelta, toColor[1] + gdelta, toColor[2] + bdelta, pixdata[x, y][3])

    return img


def generateCore():
    list1 = [1, 1, 1, 1, 1, 1, 1, 2, 2, 2, 3, 3, 4]
    choice = random.choice(list1)
    core = Image.open(f"disc_asset/disc_core_{choice}.png")

    col1 = genRandomColor(36)
    col2 = genRandomColor(36)

    core = changePNGColor(core, "#240b40", col1, 0)
    core = changePNGColor(core, "#ffffff", col2)


    return core


def genDisc(id):
    base = Image.open("disc_asset/disc_border.png")
    inner = Image.open("disc_asset/disc_inner.png")

    core = generateCore()

    maincolor = genRandomColor(48)
    secondarycolor = color_variant(maincolor, 15)
    bordercolor = color_variant(maincolor, -30)
    outercolor = color_variant(maincolor, -45)

    inner = changePNGColor(inner, "#404040", maincolor)
    inner = changePNGColor(inner, "#515151", secondarycolor)

    base = changePNGColor(base, "#262626", bordercolor)
    base = changePNGColor(base, "#111111", outercolor)

    base.paste(inner, (0, 0), inner)
    base.paste(core, (0,0), core)
    base.save(f'dist/textures/{id}.png')

def doGenDisk(id):
    try:
        genDisc(id)
    except:
        doGenDisk(id)
