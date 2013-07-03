package org.openhie.openempi.util.cmdline;

import java.math.BigInteger;
import java.security.SecureRandom;

public class DiffieHellmanExchange
{
	static final BigInteger P_DH_PARAM =
			new BigInteger( "96638233045864084518231195061482444508946745418794" +
							"32145426376197328661494156721783116267581866720348" +
							"56569235188055781021126862722394033261228353790026" +
							"74242252342519550057166057717181586900064534416478" +
							"66989456980848803906247815161337494391373137153839" +
							"33939639741821762833572485404071453236110146128678" +
							"95640659");
	static final BigInteger G_DH_PARAM =
			new BigInteger( "15747374786246668558215402439365766861880018910353" +
							"73114168675609352822845701221083803231254972889042" +
							"07855156227788690363403072390960974948248690468386" +
							"01971993935506051322384408989957908892058425561018" +
							"77319073471747739646901162574581078537710124683072" +
							"39204369669727780274795620256455014913194644458689" +
							"982153231");

	public static void main(String[] args) {
		SecureRandom rnd = new SecureRandom();

        long aSecret = Math.abs(rnd.nextLong());
        BigInteger aSecretBigInt = BigInteger.valueOf(aSecret);
        BigInteger A = G_DH_PARAM.modPow(aSecretBigInt, P_DH_PARAM);

        byte[] alicePubKeyEnc = A.toByteArray();

        long bSecret = Math.abs(rnd.nextLong());
        BigInteger bSecretBigInt = BigInteger.valueOf(bSecret);
        BigInteger B = G_DH_PARAM.modPow(bSecretBigInt, P_DH_PARAM);

        byte[] bobPubKeyEnc = B.toByteArray();

        BigInteger AatBob = new BigInteger(alicePubKeyEnc);
        BigInteger BatAlice = new BigInteger(bobPubKeyEnc);

        BigInteger sAliceBigInt = BatAlice.modPow(aSecretBigInt, P_DH_PARAM);
        long sharedSecretAtAlice = sAliceBigInt.mod(BigInteger.valueOf(Long.MAX_VALUE)).longValue();
        System.out.println("Shared secret at Alice=" + sharedSecretAtAlice);

        BigInteger sBobBigInt = AatBob.modPow(bSecretBigInt, P_DH_PARAM);
        long sharedSecretAtBob = sBobBigInt.mod(BigInteger.valueOf(Long.MAX_VALUE)).longValue();
        System.out.println("Shared secret at Bob=" + sharedSecretAtBob);
	}
}