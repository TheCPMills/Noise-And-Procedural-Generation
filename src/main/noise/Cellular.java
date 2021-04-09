package main.noise;

import main.util.*;

public class Cellular extends Noise {
    DistanceFunction distanceFunction = DistanceFunction.Natural;
    CellularReturnType returnType = CellularReturnType.Distance;

    private static final Vector2[] CELL2D = { new Vector2(-0.4313539279f, 0.1281943404f),
            new Vector2(-0.1733316799, 0.415278375), new Vector2(-0.2821957395, -0.3505218461),
            new Vector2(-0.2806473808, 0.3517627718), new Vector2(0.3125508975, -0.3237467165),
            new Vector2(0.3383018443, -0.2967353402), new Vector2(-0.4393982022, -0.09710417025),
            new Vector2(-0.4460443703, -0.05953502905), new Vector2(-0.302223039, 0.3334085102),
            new Vector2(-0.212681052, -0.3965687458), new Vector2(-0.2991156529, 0.3361990872),
            new Vector2(0.2293323691, 0.3871778202), new Vector2(0.4475439151, -0.04695150755),
            new Vector2(0.1777518, 0.41340573), new Vector2(0.1688522499, -0.4171197882),
            new Vector2(-0.0976597166, 0.4392750616), new Vector2(0.08450188373, 0.4419948321),
            new Vector2(-0.4098760448, -0.1857461384), new Vector2(0.3476585782, -0.2857157906),
            new Vector2(-0.3350670039, -0.30038326), new Vector2(0.2298190031, -0.3868891648),
            new Vector2(-0.01069924099, 0.449872789), new Vector2(-0.4460141246, -0.05976119672),
            new Vector2(0.3650293864, 0.2631606867), new Vector2(-0.349479423, 0.2834856838),
            new Vector2(-0.4122720642, 0.1803655873), new Vector2(-0.267327811, 0.3619887311),
            new Vector2(0.322124041, -0.3142230135), new Vector2(0.2880445931, -0.3457315612),
            new Vector2(0.3892170926, -0.2258540565), new Vector2(0.4492085018, -0.02667811596),
            new Vector2(-0.4497724772, 0.01430799601), new Vector2(0.1278175387, -0.4314657307),
            new Vector2(-0.03572100503, 0.4485799926), new Vector2(-0.4297407068, -0.1335025276),
            new Vector2(-0.3217817723, 0.3145735065), new Vector2(-0.3057158873, 0.3302087162),
            new Vector2(-0.414503978, 0.1751754899), new Vector2(-0.3738139881, 0.2505256519),
            new Vector2(0.2236891408, -0.3904653228), new Vector2(0.002967775577, -0.4499902136),
            new Vector2(0.1747128327, -0.4146991995), new Vector2(-0.4423772489, -0.08247647938),
            new Vector2(-0.2763960987, -0.355112935), new Vector2(-0.4019385906, -0.2023496216),
            new Vector2(0.3871414161, -0.2293938184), new Vector2(-0.430008727, 0.1326367019),
            new Vector2(-0.03037574274, -0.4489736231), new Vector2(-0.3486181573, 0.2845441624),
            new Vector2(0.04553517144, -0.4476902368), new Vector2(-0.0375802926, 0.4484280562),
            new Vector2(0.3266408905, 0.3095250049), new Vector2(0.06540017593, -0.4452222108),
            new Vector2(0.03409025829, 0.448706869), new Vector2(-0.4449193635, 0.06742966669),
            new Vector2(-0.4255936157, -0.1461850686), new Vector2(0.449917292, 0.008627302568),
            new Vector2(0.05242606404, 0.4469356864), new Vector2(-0.4495305179, -0.02055026661),
            new Vector2(-0.1204775703, 0.4335725488), new Vector2(-0.341986385, -0.2924813028),
            new Vector2(0.3865320182, 0.2304191809), new Vector2(0.04506097811, -0.447738214),
            new Vector2(-0.06283465979, 0.4455915232), new Vector2(0.3932600341, -0.2187385324),
            new Vector2(0.4472261803, -0.04988730975), new Vector2(0.3753571011, -0.2482076684),
            new Vector2(-0.273662295, 0.357223947), new Vector2(0.1700461538, 0.4166344988),
            new Vector2(0.4102692229, 0.1848760794), new Vector2(0.323227187, -0.3130881435),
            new Vector2(-0.2882310238, -0.3455761521), new Vector2(0.2050972664, 0.4005435199),
            new Vector2(0.4414085979, -0.08751256895), new Vector2(-0.1684700334, 0.4172743077),
            new Vector2(-0.003978032396, 0.4499824166), new Vector2(-0.2055133639, 0.4003301853),
            new Vector2(-0.006095674897, -0.4499587123), new Vector2(-0.1196228124, -0.4338091548),
            new Vector2(0.3901528491, -0.2242337048), new Vector2(0.01723531752, 0.4496698165),
            new Vector2(-0.3015070339, 0.3340561458), new Vector2(-0.01514262423, -0.4497451511),
            new Vector2(-0.4142574071, -0.1757577897), new Vector2(-0.1916377265, -0.4071547394),
            new Vector2(0.3749248747, 0.2488600778), new Vector2(-0.2237774255, 0.3904147331),
            new Vector2(-0.4166343106, -0.1700466149), new Vector2(0.3619171625, 0.267424695),
            new Vector2(0.1891126846, -0.4083336779), new Vector2(-0.3127425077, 0.323561623),
            new Vector2(-0.3281807787, 0.307891826), new Vector2(-0.2294806661, 0.3870899429),
            new Vector2(-0.3445266136, 0.2894847362), new Vector2(-0.4167095422, -0.1698621719),
            new Vector2(-0.257890321, -0.3687717212), new Vector2(-0.3612037825, 0.2683874578),
            new Vector2(0.2267996491, 0.3886668486), new Vector2(0.207157062, 0.3994821043),
            new Vector2(0.08355176718, -0.4421754202), new Vector2(-0.4312233307, 0.1286329626),
            new Vector2(0.3257055497, 0.3105090899), new Vector2(0.177701095, -0.4134275279),
            new Vector2(-0.445182522, 0.06566979625), new Vector2(0.3955143435, 0.2146355146),
            new Vector2(-0.4264613988, 0.1436338239), new Vector2(-0.3793799665, -0.2420141339),
            new Vector2(0.04617599081, -0.4476245948), new Vector2(-0.371405428, -0.2540826796),
            new Vector2(0.2563570295, -0.3698392535), new Vector2(0.03476646309, 0.4486549822),
            new Vector2(-0.3065454405, 0.3294387544), new Vector2(-0.2256979823, 0.3893076172),
            new Vector2(0.4116448463, -0.1817925206), new Vector2(-0.2907745828, -0.3434387019),
            new Vector2(0.2842278468, -0.348876097), new Vector2(0.3114589359, -0.3247973695),
            new Vector2(0.4464155859, -0.0566844308), new Vector2(-0.3037334033, -0.3320331606),
            new Vector2(0.4079607166, 0.1899159123), new Vector2(-0.3486948919, -0.2844501228),
            new Vector2(0.3264821436, 0.3096924441), new Vector2(0.3211142406, 0.3152548881),
            new Vector2(0.01183382662, 0.4498443737), new Vector2(0.4333844092, 0.1211526057),
            new Vector2(0.3118668416, 0.324405723), new Vector2(-0.272753471, 0.3579183483),
            new Vector2(-0.422228622, -0.1556373694), new Vector2(-0.1009700099, -0.4385260051),
            new Vector2(-0.2741171231, -0.3568750521), new Vector2(-0.1465125133, 0.4254810025),
            new Vector2(0.2302279044, -0.3866459777), new Vector2(-0.3699435608, 0.2562064828),
            new Vector2(0.105700352, -0.4374099171), new Vector2(-0.2646713633, 0.3639355292),
            new Vector2(0.3521828122, 0.2801200935), new Vector2(-0.1864187807, -0.4095705534),
            new Vector2(0.1994492955, -0.4033856449), new Vector2(0.3937065066, 0.2179339044),
            new Vector2(-0.3226158377, 0.3137180602), new Vector2(0.3796235338, 0.2416318948),
            new Vector2(0.1482921929, 0.4248640083), new Vector2(-0.407400394, 0.1911149365),
            new Vector2(0.4212853031, 0.1581729856), new Vector2(-0.2621297173, 0.3657704353),
            new Vector2(-0.2536986953, -0.3716678248), new Vector2(-0.2100236383, 0.3979825013),
            new Vector2(0.3624152444, 0.2667493029), new Vector2(-0.3645038479, -0.2638881295),
            new Vector2(0.2318486784, 0.3856762766), new Vector2(-0.3260457004, 0.3101519002),
            new Vector2(-0.2130045332, -0.3963950918), new Vector2(0.3814998766, -0.2386584257),
            new Vector2(-0.342977305, 0.2913186713), new Vector2(-0.4355865605, 0.1129794154),
            new Vector2(-0.2104679605, 0.3977477059), new Vector2(0.3348364681, -0.3006402163),
            new Vector2(0.3430468811, 0.2912367377), new Vector2(-0.2291836801, -0.3872658529),
            new Vector2(0.2547707298, -0.3709337882), new Vector2(0.4236174945, -0.151816397),
            new Vector2(-0.15387742, 0.4228731957), new Vector2(-0.4407449312, 0.09079595574),
            new Vector2(-0.06805276192, -0.444824484), new Vector2(0.4453517192, -0.06451237284),
            new Vector2(0.2562464609, -0.3699158705), new Vector2(0.3278198355, -0.3082761026),
            new Vector2(-0.4122774207, -0.1803533432), new Vector2(0.3354090914, -0.3000012356),
            new Vector2(0.446632869, -0.05494615882), new Vector2(-0.1608953296, 0.4202531296),
            new Vector2(-0.09463954939, 0.4399356268), new Vector2(-0.02637688324, -0.4492262904),
            new Vector2(0.447102804, -0.05098119915), new Vector2(-0.4365670908, 0.1091291678),
            new Vector2(-0.3959858651, 0.2137643437), new Vector2(-0.4240048207, -0.1507312575),
            new Vector2(-0.3882794568, 0.2274622243), new Vector2(-0.4283652566, -0.1378521198),
            new Vector2(0.3303888091, 0.305521251), new Vector2(0.3321434919, -0.3036127481),
            new Vector2(-0.413021046, -0.1786438231), new Vector2(0.08403060337, -0.4420846725),
            new Vector2(-0.3822882919, 0.2373934748), new Vector2(-0.3712395594, -0.2543249683),
            new Vector2(0.4472363971, -0.04979563372), new Vector2(-0.4466591209, 0.05473234629),
            new Vector2(0.0486272539, -0.4473649407), new Vector2(-0.4203101295, -0.1607463688),
            new Vector2(0.2205360833, 0.39225481), new Vector2(-0.3624900666, 0.2666476169),
            new Vector2(-0.4036086833, -0.1989975647), new Vector2(0.2152727807, 0.3951678503),
            new Vector2(-0.4359392962, -0.1116106179), new Vector2(0.4178354266, 0.1670735057),
            new Vector2(0.2007630161, 0.4027334247), new Vector2(-0.07278067175, -0.4440754146),
            new Vector2(0.3644748615, -0.2639281632), new Vector2(-0.4317451775, 0.126870413),
            new Vector2(-0.297436456, 0.3376855855), new Vector2(-0.2998672222, 0.3355289094),
            new Vector2(-0.2673674124, 0.3619594822), new Vector2(0.2808423357, 0.3516071423),
            new Vector2(0.3498946567, 0.2829730186), new Vector2(-0.2229685561, 0.390877248),
            new Vector2(0.3305823267, 0.3053118493), new Vector2(-0.2436681211, -0.3783197679),
            new Vector2(-0.03402776529, 0.4487116125), new Vector2(-0.319358823, 0.3170330301),
            new Vector2(0.4454633477, -0.06373700535), new Vector2(0.4483504221, 0.03849544189),
            new Vector2(-0.4427358436, -0.08052932871), new Vector2(0.05452298565, 0.4466847255),
            new Vector2(-0.2812560807, 0.3512762688), new Vector2(0.1266696921, 0.4318041097),
            new Vector2(-0.3735981243, 0.2508474468), new Vector2(0.2959708351, -0.3389708908),
            new Vector2(-0.3714377181, 0.254035473), new Vector2(-0.404467102, -0.1972469604),
            new Vector2(0.1636165687, -0.419201167), new Vector2(0.3289185495, -0.3071035458),
            new Vector2(-0.2494824991, -0.3745109914), new Vector2(0.03283133272, 0.4488007393),
            new Vector2(-0.166306057, -0.4181414777), new Vector2(-0.106833179, 0.4371346153),
            new Vector2(0.06440260376, -0.4453676062), new Vector2(-0.4483230967, 0.03881238203),
            new Vector2(-0.421377757, -0.1579265206), new Vector2(0.05097920662, -0.4471030312),
            new Vector2(0.2050584153, -0.4005634111), new Vector2(0.4178098529, -0.167137449),
            new Vector2(-0.3565189504, -0.2745801121), new Vector2(0.4478398129, 0.04403977727),
            new Vector2(-0.3399999602, -0.2947881053), new Vector2(0.3767121994, 0.2461461331),
            new Vector2(-0.3138934434, 0.3224451987), new Vector2(-0.1462001792, -0.4255884251),
            new Vector2(0.3970290489, -0.2118205239), new Vector2(0.4459149305, -0.06049689889),
            new Vector2(-0.4104889426, -0.1843877112), new Vector2(0.1475103971, -0.4251360756),
            new Vector2(0.09258030352, 0.4403735771), new Vector2(-0.1589664637, -0.4209865359),
            new Vector2(0.2482445008, 0.3753327428), new Vector2(0.4383624232, -0.1016778537),
            new Vector2(0.06242802956, 0.4456486745), new Vector2(0.2846591015, -0.3485243118),
            new Vector2(-0.344202744, -0.2898697484), new Vector2(0.1198188883, -0.4337550392),
            new Vector2(-0.243590703, 0.3783696201), new Vector2(0.2958191174, -0.3391033025),
            new Vector2(-0.1164007991, 0.4346847754), new Vector2(0.1274037151, -0.4315881062),
            new Vector2(0.368047306, 0.2589231171), new Vector2(0.2451436949, 0.3773652989),
            new Vector2(-0.4314509715, 0.12786735) };

    public Cellular(int seed) {
        super(seed);
    }

    public double getNoise(double x, double y) {
        x *= frequency;
        y *= frequency;

        int xr = (x >= 0) ? (int) (x + 0.5) : (int) (x - 0.5);
        int yr = (y >= 0) ? (int) (y + 0.5) : (int) (y - 0.5);

        double distance = Double.POSITIVE_INFINITY;
        int xc = 0, yc = 0;

        switch (distanceFunction) {
            case Euclidean:
                for (int xi = xr - 1; xi <= xr + 1; xi++) {
                    for (int yi = yr - 1; yi <= yr + 1; yi++) {
                        Vector2 vec = CELL2D[hash2D(seed, xi, yi) & 255];

                        double vecX = xi - x + vec.getX();
                        double vecY = yi - y + vec.getY();

                        double newDistance = vecX * vecX + vecY * vecY;

                        if (newDistance < distance) {
                            distance = newDistance;
                            xc = xi;
                            yc = yi;
                        }
                    }
                }
                break;
            case Manhattan:
                for (int xi = xr - 1; xi <= xr + 1; xi++) {
                    for (int yi = yr - 1; yi <= yr + 1; yi++) {
                        Vector2 vec = CELL2D[hash2D(seed, xi, yi) & 255];

                        double vecX = xi - x + vec.getX();
                        double vecY = yi - y + vec.getY();

                        double newDistance = (Math.abs(vecX) + Math.abs(vecY));

                        if (newDistance < distance) {
                            distance = newDistance;
                            xc = xi;
                            yc = yi;
                        }
                    }
                }
                break;
            case Natural:
                for (int xi = xr - 1; xi <= xr + 1; xi++) {
                    for (int yi = yr - 1; yi <= yr + 1; yi++) {
                        Vector2 vec = CELL2D[hash2D(seed, xi, yi) & 255];

                        double vecX = xi - x + vec.getX();
                        double vecY = yi - y + vec.getY();

                        double newDistance = (Math.abs(vecX) + Math.abs(vecY)) + (vecX * vecX + vecY * vecY);

                        if (newDistance < distance) {
                            distance = newDistance;
                            xc = xi;
                            yc = yi;
                        }
                    }
                }
                break;
            default:
        }

        switch (returnType) {
        case CellValue:
            return value2D(0, xc, yc);
        case Distance:
            return distance - 1;
        default:
            return 0;
        }
    }
}

enum DistanceFunction {
    Euclidean, Manhattan, Natural;
}

enum CellularReturnType {
    CellValue, Distance;
}