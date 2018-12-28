package kafka;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;

import java.util.Properties;

public class CustomProducer {
    public static void main(String[] args) {
        Properties props = new Properties();
        // Kafka服务端的主机名和端口号
        props.put("bootstrap.servers", "192.168.25.10:9092,192.168.25.11:9092,192.168.25.12:9092");
        // 等待所有副本节点的应答
        props.put("acks", "all");
        // 消息发送最大尝试次数
        props.put("retries", 0);
        // 一批消息处理大小。是数据在生产者这边，发送的时候的缓存大小
        props.put("batch.size", 16384);
        // 请求延时。此时生成的数据不是直接发送给集群，而是5ms之后，再发送给服务器。所以此时是将5ms之内的数据全部
        // 发送给服务器。所以发送的是一批数据。可以降低io的消耗
        props.put("linger.ms", 5);
        // 发送缓存区内存大小。这个缓冲区是数据写到本地的.log文件里面的时候内存的缓冲区大小
        props.put("buffer.memory", 33554432);
        // key序列化.序列化的时候写的类名字官方文档里面没有，需要去如下的位置寻找。在使用maven导入的jar包里面去找。org.apache.kafka.common.serialization
        props.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        // value序列化
        props.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");


        KafkaProducer<String, String> producer = new KafkaProducer<String, String>(props);

        String value = "{\n" +
                "   \"data\": {\n" +
                "      \"app_info\": \"2 | 3064715 | 867561035179947 | {\\\"app\\\":\\\"com.android.cts.priv.ctsshim,com.android.cts.priv.ctsshim,YouTube,SampleExtAuthService,Perfdump,Android Services Library,My Smartfren,Penyimpanan Ponsel dan Pesan,GFManager,Google,Penyimpanan Kalender,Penyimpanan Media,com.qti.service.colorservice,Google One Time Init,Android Shared Library,com.android.wallpapercropper,com.quicinc.cne.CNEService.CNEServiceApp,com.mi.setupwizardoverlay,com.qualcomm.qti.autoregistration,Galeri,Balls Bricks Breaker 2,Candy Crush Soda,File,Penyimpanan Eksternal,Penampil HTML,SVI Settings,PicsArt,RCSService,Companion Device Manager,MmsService,Pengelola Download,SampleAuthenticatorService,Message,CallEnhancement,Young.Live,SecProtect,com.qualcomm.qti.telephonyservice,FidoCryptoService,SUPER PINGER - Anti Lag,ConfigUpdater,Pembantu Akses Paket,Conference URI Dialer,Wps Wpa Tester,Download,Google Play Store,PacProcessor,Content Adaptive Backlight Settings,Pemasang Sertifikat,com.android.carrierconfig,TalkBack,org.codeaurora.bluetooth,Penyiapan perangkat,com.qti.qualcomm.datastatusnotification,Sistem Android,com.qualcomm.qti.callfeaturessetting,Wfd Service,TIX ID,Device Info,Keyboard Indic Google,Android Easter Egg,Host MTP,SIM Toolkit,Launcher3,com.android.backupconfirm,Dirac Control Service,fingerprint test,Jam,org.codeaurora.ims,Intent Filter Verification Service,Gmail,Duo,Dark,FactoryKit Test,YMusic,SecureExtAuthService,Penyiapan Android,com.qualcomm.qcrilmsgtunnel,Setelan Penyimpanan,com.android.sharedstoragebackup,Google Play Musik,Print Spooler,CLEANit,Lamunan Dasar,Snaptube,com.qualcomm.qti.qcom_accesslogkit,Mi Remote,Masukan,Perangkat Masukan,Telepon,SecureSampleAuthService,Layanan Cetak Default,com.qti.dpmserviceapp,Durango,MX Player,FingerprintExtensionService,Smart-Divert,RAR,MusicFX,Drive,Maps,Mobile Legends: Bang Bang,Siaran Seluler,Android System WebView,com.qualcomm.qti.simsettings,Kontak,Pengelolaan Panggilan Telepon,Sinkronisasi Google Kontak,Wajah Tepercaya,Key Chain,Kamera,Kalkulator,Chrome,Pemasang paket,Layanan Google Play,Kerangka Kerja Layanan Google,Carrier Services,Mesin Google Text-to-speech,com.qualcomm.qti.qtisystemservice,Call Log Backup/Restore,Penyiapan Mitra Google,Google Play Film,org.codeaurora.btmultisim,AplikasiDefaultOperator,Toolbox for Minecraft: PE,ANT HAL Service,Minecraft,ProxyHandler,Hago,CarrierLoadService,UC Browser,Spock,Agen Masukan Market,Print Service Recommendation Service,Mi Drop,Foto,Kalender,Piano Tiles 2,Penyiapan profil kerja,Sensor Test Tool,Juice Jam,Screensaver Foto,PAI,MyTelkomsel,com.android.providers.partnerbookmarks,com.android.smspush,AKULAKU,Pemilih Wallpaper Animasi,Assemble test,Lite,Masukan Korea Google,Terjemahan,Layanan Transfer Backup Google,CarrierAccessCacheService,Pengelola Penyimpanan,Bookmark Provider,Setelan,com.qualcomm.qti.ims,com.qualcomm.qti.lpa,com.qualcomm.qti.uim,Masukan Pinyin Google,Notepad cepat,Google Play Buku,LocationServices,SimContacts,Hibernation Manager,com.android.cts.ctsshim,Secure UI Service,VpnDialogs,Catatan Keep,Layanan Telepon,Kerangka,com.android.wallpaperbackup,Penyimpanan Nomor yang Diblokir,DuckDuckGo,Kamus Pengguna,Informasi darurat,TouchVPN,QMMI,Fused Location,Sistem UI,Bluetooth MIDI Service,ConfDialer,PowerOffAlarm,myXL,com.qualcomm.qti.networksetting,SiMontok,Aptoide,Mi Home,Google Play Game,Bluetooth,com.qualcomm.timeservice,com.android.wallpaperpicker,com.qualcomm.embms,Penyimpanan Kontak,CaptivePortalLogin,Marble Legend 2,Gboard,UseeTVGO\\\"}\",\n" +
                "      \"hardware_info\": \"1 | -1 |  | {\\\"cpuCurFreq\\\":\\\"2016000\\\",\\\"appVersion\\\":\\\"2.6.0\\\",\\\"isEmulator\\\":\\\"GENUINE PHONE\\\",\\\"Memory\\\":\\\"total-23897904B-max-536870912B-dalvikPss-16064B\\\",\\\"cpuArchitecture\\\":\\\"qcom\\\",\\\"isRooted\\\":\\\"NOT ROOTED\\\",\\\"appSignatures\\\":\\\"552635161--\\\",\\\"BluetoothMAC\\\":\\\"02:00:00:00:00:00\\\",\\\"Build\\\":\\\"-BOARD-msm8953\\\\n-BOOTLOADER-MSM8953_TISSOT2.0_20180920175008\\\\n-BRAND-xiaomi\\\\n-CPU_ABI-armeabi-v7a\\\\n-CPU_ABI2-armeabi\\\\n-DEVICE-tissot_sprout\\\\n-DISPLAY-OPM1.171019.026.V9.6.6.0.ODHMIFE\\\\n-FINGERPRINT-xiaomi/tissot/tissot_sprout:8.1.0/OPM1.171019.026/V9.6.6.0.ODHMIFE:user/release-keys\\\\n-HARDWARE-qcom\\\\n-HOST-mi-server\\\\n-ID-OPM1.171019.026\\\\n-MANUFACTURER-Xiaomi\\\\n-MODEL-Mi A1\\\\n-PRODUCT-tissot\\\\n-SERIAL-2821a00f9805\\\\n-RADIO-.TA.2.3.c1-00723-8953_GEN_PACK-1\\\\n-TAGS-release-keys\\\\n-TIME-1537440461000\\\\n-TYPE-user\\\\n-VERSION.RELEASE-8.1.0\\\",\\\"NetworkType\\\":\\\"13\\\",\\\"External Storage\\\":\\\"total-54091MB-free-14516MB\\\",\\\"ethIp\\\":\\\"0.0.0.0\\\",\\\"USB\\\":\\\"\\\",\\\"appName\\\":\\\"AKULAKU\\\",\\\"cpuSerial\\\":\\\"0000000000000000\\\",\\\"ip\\\":\\\"10.98.87.157\\\",\\\"Time\\\\u0026Location\\\":\\\"Asia/Jakarta  null\\\",\\\"AndroidId\\\":\\\"0864935e68497097\\\",\\\"cpuName\\\":\\\"0\\\",\\\"Sim information\\\":\\\"simState:5\\\\nimsi:510104162839670\\\\nsimSerial:8962100541628396702\\\\nphoneNumber:\\\\nphoneType:1\\\\ncarrierMobileCountryCode:id\\\\ncarrierOperator:TELKOMSEL\\\\nsimCountryIso:id\\\\nmcc:510\\\\nmnc:2147483647\\\\nlac:2147483647\\\\ncid:2147483647\\\\nsimStrength:-97\\\",\\\"Sys Features\\\":\\\"-WIFI-1-LOCATION_GPS-1-TELEPHONY-1-NFC-0-NFC_HOST_CARD_EMULATION-0-BLUETOOTH-1-WIFI_DIRECT-1-USB_HOST-1-USB_ACCESSORY-1\\\",\\\"cpuMaxFreq\\\":\\\"2016000\\\",\\\"appPackageName\\\":\\\"io.silvrr.installment\\\",\\\"Display\\\":\\\"density-2.55-width-1080.0-height-1920.0\\\",\\\"cpuMinFreq\\\":\\\"652800\\\"}\",\n" +
                "      \"uid\": \"3064715\",\n" +
                "      \"device_id\": \"867561035179947\"\n" +
                "   },\n" +
                "   \"createTime\": 1539402104044\n" +
                "}";


		for (int i = 1; i < 1000; i++)
		{
			producer.send(new ProducerRecord<String, String>("riskDeviceFingerprint", Integer.toString(i), value));
		}




//下面的发送方法有许多重载的。可以具体的去看，比如可以指定发送到的key，和分区号，如果指定了分区号。可以将数据发送到指定的分区。
//        producer.send(new ProducerRecord<String, String>("riskDeviceFingerprint", value));


        producer.close();
    }
}
