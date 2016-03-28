package br.inpe.worldwind.view;

import java.awt.Color;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ColorBrewer {
	
	private Map<String, List<String>> YlGn;
	private Map<String, List<String>> YlGnBu;
	private Map<String, List<String>> GnBu;
	private Map<String, List<String>> BuGn;
	private Map<String, List<String>> PuBuGn;
	private Map<String, List<String>> PuBu;
	private Map<String, List<String>> BuPu;
	private Map<String, List<String>> RdPu;
	private Map<String, List<String>> PuRd;
	private Map<String, List<String>> OrRd;
	private Map<String, List<String>> YlOrRd;
	private Map<String, List<String>> YlOrBr;
	private Map<String, List<String>> Purples;
	private Map<String, List<String>> Blues;
	private Map<String, List<String>> Greens;
	private Map<String, List<String>> Oranges;
	private Map<String, List<String>> Reds;
	private Map<String, List<String>> Greys;
	private Map<String, List<String>> PuOr;
	private Map<String, List<String>> BrBG;
	private Map<String, List<String>> PRGn;
	private Map<String, List<String>> PiYG;
	private Map<String, List<String>> RdBu;
	private Map<String, List<String>> RdGy;
	private Map<String, List<String>> RdYlBu;
	private Map<String, List<String>> Spectral;
	private Map<String, List<String>> RdYlGn;
	private Map<String, List<String>> Accent;
	private Map<String, List<String>> Dark2;
	private Map<String, List<String>> Paired;
	private Map<String, List<String>> Pastel1;
	private Map<String, List<String>> Pastel2;
	private Map<String, List<String>> Set1;
	private Map<String, List<String>> Set2;
	private Map<String, List<String>> Set3;
	
	/**
	 * Transform methods
	 * 
	 * @return
	 */
	public Map<String, List<java.awt.Color>> getAwtColors(Map<String, List<String>> colors) {
		Map<String, List<java.awt.Color>> awtColors = new HashMap<>();
		colors.forEach((k, v) -> {
			List<java.awt.Color> listOfColors = new ArrayList<>();
			v.forEach(c -> {
				java.awt.Color value = Color.decode(c);
				if (value == null)
					value = java.awt.Color.black;
				listOfColors.add(value);
			});
			awtColors.put(k, listOfColors);
		});
		return awtColors;
	}
	
	/*Getters and setters*/

	public Map<String, List<String>> getYlGn() {
		return YlGn;
	}

	public void setYlGn(Map<String, List<String>> ylGn) {
		YlGn = ylGn;
	}

	public Map<String, List<String>> getYlGnBu() {
		return YlGnBu;
	}

	public void setYlGnBu(Map<String, List<String>> ylGnBu) {
		YlGnBu = ylGnBu;
	}

	public Map<String, List<String>> getGnBu() {
		return GnBu;
	}

	public void setGnBu(Map<String, List<String>> gnBu) {
		GnBu = gnBu;
	}

	public Map<String, List<String>> getBuGn() {
		return BuGn;
	}

	public void setBuGn(Map<String, List<String>> buGn) {
		BuGn = buGn;
	}

	public Map<String, List<String>> getPuBuGn() {
		return PuBuGn;
	}

	public void setPuBuGn(Map<String, List<String>> puBuGn) {
		PuBuGn = puBuGn;
	}

	public Map<String, List<String>> getPuBu() {
		return PuBu;
	}

	public void setPuBu(Map<String, List<String>> puBu) {
		PuBu = puBu;
	}

	public Map<String, List<String>> getBuPu() {
		return BuPu;
	}

	public void setBuPu(Map<String, List<String>> buPu) {
		BuPu = buPu;
	}

	public Map<String, List<String>> getRdPu() {
		return RdPu;
	}

	public void setRdPu(Map<String, List<String>> rdPu) {
		RdPu = rdPu;
	}

	public Map<String, List<String>> getPuRd() {
		return PuRd;
	}

	public void setPuRd(Map<String, List<String>> puRd) {
		PuRd = puRd;
	}

	public Map<String, List<String>> getOrRd() {
		return OrRd;
	}

	public void setOrRd(Map<String, List<String>> orRd) {
		OrRd = orRd;
	}

	public Map<String, List<String>> getYlOrRd() {
		return YlOrRd;
	}

	public void setYlOrRd(Map<String, List<String>> ylOrRd) {
		YlOrRd = ylOrRd;
	}

	public Map<String, List<String>> getYlOrBr() {
		return YlOrBr;
	}

	public void setYlOrBr(Map<String, List<String>> ylOrBr) {
		YlOrBr = ylOrBr;
	}

	public Map<String, List<String>> getPurples() {
		return Purples;
	}

	public void setPurples(Map<String, List<String>> purples) {
		Purples = purples;
	}

	public Map<String, List<String>> getBlues() {
		return Blues;
	}

	public void setBlues(Map<String, List<String>> blues) {
		Blues = blues;
	}

	public Map<String, List<String>> getGreens() {
		return Greens;
	}

	public void setGreens(Map<String, List<String>> greens) {
		Greens = greens;
	}

	public Map<String, List<String>> getOranges() {
		return Oranges;
	}

	public void setOranges(Map<String, List<String>> oranges) {
		Oranges = oranges;
	}

	public Map<String, List<String>> getReds() {
		return Reds;
	}

	public void setReds(Map<String, List<String>> reds) {
		Reds = reds;
	}

	public Map<String, List<String>> getGreys() {
		return Greys;
	}

	public void setGreys(Map<String, List<String>> greys) {
		Greys = greys;
	}

	public Map<String, List<String>> getPuOr() {
		return PuOr;
	}

	public void setPuOr(Map<String, List<String>> puOr) {
		PuOr = puOr;
	}

	public Map<String, List<String>> getBrBG() {
		return BrBG;
	}

	public void setBrBG(Map<String, List<String>> brBG) {
		BrBG = brBG;
	}

	public Map<String, List<String>> getPRGn() {
		return PRGn;
	}

	public void setPRGn(Map<String, List<String>> pRGn) {
		PRGn = pRGn;
	}

	public Map<String, List<String>> getPiYG() {
		return PiYG;
	}

	public void setPiYG(Map<String, List<String>> piYG) {
		PiYG = piYG;
	}

	public Map<String, List<String>> getRdBu() {
		return RdBu;
	}

	public void setRdBu(Map<String, List<String>> rdBu) {
		RdBu = rdBu;
	}

	public Map<String, List<String>> getRdGy() {
		return RdGy;
	}

	public void setRdGy(Map<String, List<String>> rdGy) {
		RdGy = rdGy;
	}

	public Map<String, List<String>> getRdYlBu() {
		return RdYlBu;
	}

	public void setRdYlBu(Map<String, List<String>> rdYlBu) {
		RdYlBu = rdYlBu;
	}

	public Map<String, List<String>> getSpectral() {
		return Spectral;
	}

	public void setSpectral(Map<String, List<String>> spectral) {
		Spectral = spectral;
	}

	public Map<String, List<String>> getRdYlGn() {
		return RdYlGn;
	}

	public void setRdYlGn(Map<String, List<String>> rdYlGn) {
		RdYlGn = rdYlGn;
	}

	public Map<String, List<String>> getAccent() {
		return Accent;
	}

	public void setAccent(Map<String, List<String>> accent) {
		Accent = accent;
	}

	public Map<String, List<String>> getDark2() {
		return Dark2;
	}

	public void setDark2(Map<String, List<String>> dark2) {
		Dark2 = dark2;
	}

	public Map<String, List<String>> getPaired() {
		return Paired;
	}

	public void setPaired(Map<String, List<String>> paired) {
		Paired = paired;
	}

	public Map<String, List<String>> getPastel1() {
		return Pastel1;
	}

	public void setPastel1(Map<String, List<String>> pastel1) {
		Pastel1 = pastel1;
	}

	public Map<String, List<String>> getPastel2() {
		return Pastel2;
	}

	public void setPastel2(Map<String, List<String>> pastel2) {
		Pastel2 = pastel2;
	}

	public Map<String, List<String>> getSet1() {
		return Set1;
	}

	public void setSet1(Map<String, List<String>> set1) {
		Set1 = set1;
	}

	public Map<String, List<String>> getSet2() {
		return Set2;
	}

	public void setSet2(Map<String, List<String>> set2) {
		Set2 = set2;
	}

	public Map<String, List<String>> getSet3() {
		return Set3;
	}

	public void setSet3(Map<String, List<String>> set3) {
		Set3 = set3;
	}
}
