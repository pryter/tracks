package me.pryter.tracks.utils;

import me.pryter.tracks.registry.Items;
import net.minecraft.item.Item;
import java.util.Random;

public class RandomDisc {

    public static Item[] discs = {
        Items.Disc_PeeMaiDaiDukJao,
        Items.Disc_Phing,
        Items.disc_x8078xzh,
        Items.disc_z1pphltr,
        Items.disc_k1h73gmp,
        Items.disc_bqbuobw3,    
        Items.disc_dofo6ttr,
        Items.disc_v8eueiyd,
        Items.disc_vgkxvgo3,
        Items.disc_47823uby,
        Items.disc_vb0p4s6f,
        Items.disc_idi5w82b,
        Items.disc_66rzr7ok,
        Items.disc_qdzrbgc2,
        Items.disc_7ij5ix2k,
        Items.disc_3w2t0do2,
        Items.disc_0gqpycfb,
        Items.disc_bwo9gbc6,
        Items.disc_woauyav4,
        Items.disc_8vgvp5fl,
        Items.disc_1m20au1s,
        Items.disc_igndph10,
        Items.disc_wd4m8k4l,
        Items.disc_7wtm5qzd,
        Items.disc_jrd7feys,
        Items.disc_p8hgabq0,
        Items.disc_btrrckrk,
        Items.disc_s4cqv3m2,
        Items.disc_ya60v1uu,
        Items.disc_raf3qt8r,
        Items.disc_n0wtf804,
        Items.disc_o7w3z3zm,
        Items.disc_ggx0iire,
        Items.disc_xkyi8an4,
        Items.disc_lzvozbp9,
        Items.disc_t29mkozo,
        Items.disc_ve4toq8c,
        Items.disc_f7cv2mlh,
        Items.disc_oys4lghy,
        Items.disc_7mchg7d7,
        Items.disc_heeny4a1,
        Items.disc_6uqyhapp,
        Items.disc_e0mddejc,
        Items.disc_3r6io75n,
        Items.disc_u9g0jjsq,
        Items.disc_x1otw63e,
        Items.disc_q5m1af3q,
        Items.disc_o0zan69w,
        Items.disc_i778cfwk,
        Items.disc_k7gic9q2,
        Items.disc_zoxfimbh,
        Items.disc_etl77ae0,
        Items.disc_kjpabh4h,
        Items.disc_adnu89f1,
        Items.disc_4qlfxgfy,
        Items.disc_5uuhll7m,
        Items.disc_pj7lgf5s,
        Items.disc_4qi6fuic,
        Items.disc_6ed8iul9,
        Items.disc_5pcfav8g,
        Items.disc_dn9ubv1s,
        Items.disc_n2v566qz,
        Items.disc_b5u60ru6,
        Items.disc_lb8s6gck,
        Items.disc_4aouzrp1,
        Items.disc_tq8zax4i,
        Items.disc_bm11mp1t,
        Items.disc_unfwn3ma,
        Items.disc_a81h2f9l,
        Items.disc_umxu1w6u,
        Items.disc_lqs0d985,
        Items.disc_idnhksny,
        Items.disc_nh0woo0d,
        Items.disc_tlyprb5x,
        Items.disc_t0swvxji,
        Items.disc_jsgbxyp5
    };

    public static Item getRandomDisk() {
        int rnd = new Random().nextInt(discs.length);
        return discs[rnd];
    }
}