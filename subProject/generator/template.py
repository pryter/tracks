

def createSapEvent(trackId):
    return f'''public static final SoundEvent {trackId} = registerTrack("{trackId}");
    '''

def createEvent(extra):
    return """
package me.pryter.tracks.registry;

import net.minecraft.sound.SoundEvent;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class Events {

    private static SoundEvent registerTrack(String name) {
        Identifier id = new Identifier("tracks", name);
        return Registry.register(Registry.SOUND_EVENT, id, new SoundEvent(id));
    }

    public static final SoundEvent Track_PeeMaiDaiDukJao = registerTrack("track_peemaidaidukjao");
    public static final SoundEvent Track_Phing = registerTrack("track_phing");

    """+ extra +"""

    public static void init() { }
}
"""

def createSapItem(discId, trackId):
    return f'''public static final Item {discId} = new DiscItem().register("{discId}", Events.{trackId});
    '''

def createItem(extra):
    return"""
package me.pryter.tracks.registry;

import me.pryter.tracks.items.DiscItem;
import net.minecraft.item.Item;

public class Items {
    public static final Item Disc_PeeMaiDaiDukJao = new DiscItem().register("disc_peemaidaidukjao", Events.Track_PeeMaiDaiDukJao);
    public static final Item Disc_Phing = new DiscItem().register("disc_phing", Events.Track_Phing);

    """+ extra +"""

    public static void init() { }
}
"""

def createSapLang(discId, title):
    return f"""
    ,"item.tracks.{discId}": "Music Disc",
    "item.tracks.{discId}.desc": "{title}"
    """

def createLang(extra):
    return"""
{
    "item.tracks.disc_peemaidaidukjao": "Music Disc",
    "item.tracks.disc_peemaidaidukjao.desc": "Aj.Mairom - Pee Mai Dai Duk Jao" ,
    "item.tracks.disc_phing": "Music Disc",
    "item.tracks.disc_phing.desc": "Nont Thanont - Phing"
    """+ extra + """ 
}
"""

def createModel(discId):
    return """
{
    "parent": "minecraft:item/generated",
    "textures": {
      "layer0": "tracks:item/"""+ discId +""""
    }
  } 
"""

def createSapSound(trackId, id): 
    return """
    ,
    \""""+ trackId+"""\": {
        "sounds": [
            {
                "name": "tracks:records/"""+id+"""\",
                "stream": true
            }
        ]
    }
    """

def createSound(extra):
    return"""
{
    "track_peemaidaidukjao": {
        "sounds": [
            {
                "name": "tracks:records/peemaidaidukjao",
                "stream": true
            }
        ]
    },
    "track_phing": {
        "sounds": [
            {
                "name": "tracks:records/phing",
                "stream": true
            }
        ]
    }
    """+extra+"""
}
"""