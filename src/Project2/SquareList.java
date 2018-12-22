package Project2;

import java.util.ArrayList;
import java.util.List;

public class SquareList {
	List<Square> intial() {
		Square b = new Square();
		b.setId(0);
		List<Square> squares = new ArrayList<Square>();
		squares.add(b);
		Land a = new Land();
		a.setId(1);
		a.setBuyprice(200);
		a.setBuildprice(100);
		a.setItemName("利比亚");
		int tmp[] = new int[5];
		for (int i = 0; i < 5; i++)
			tmp[i] = (int) (a.getBuyprice() * 0.5) * (i+1);
		a.setPaidmoney(tmp);
		Land c = new Land();
		c.setId(2);
		c.setBuyprice(220);
		c.setItemName("苏丹");
		c.setBuildprice(100);
		for (int i = 0; i < 5; i++)
			tmp[i] = (int) (c.getBuyprice() * 0.5) * (i+1);
		c.setPaidmoney(tmp);
		Land d = new Land();
		d.setId(3);
		d.setBuyprice(250);
		d.setItemName("土耳其");
		d.setBuildprice(100);
		for (int i = 0; i < 5; i++)
			tmp[i] = (int) (d.getBuyprice() * 0.5) * (i+1);
		d.setPaidmoney(tmp);
		List<Land> neigh = new ArrayList<Land>();
		neigh.add(c);
		neigh.add(d);
		a.setNeighbour(neigh);
		squares.add(a);
		List<Land> neigh1 = new ArrayList<Land>();
		neigh1.add(a);
		neigh1.add(d);
		c.setNeighbour(neigh);
		squares.add(c);
		List<Land> neigh2 = new ArrayList<Land>();
		neigh2.add(a);
		neigh2.add(c);
		d.setNeighbour(neigh2);
		squares.add(d);
		Square e = new Square();
		e.setId(4);
		squares.add(e);
		Square f = new Square();
		f.setId(5);
		squares.add(f);
		Square g = new Square();
		g.setId(6);
		squares.add(g);

		Land h = new Land();
		h.setId(7);
		h.setBuyprice(200);
		h.setItemName("希腊");
		h.setBuildprice(100);
		for (int i = 0; i < 5; i++)
			tmp[i] = (int) (h.getBuyprice() * 0.5) * (i+1);
		h.setPaidmoney(tmp);
		Land i1 = new Land();
		i1.setId(8);
		i1.setBuyprice(220);
		i1.setItemName("保加利亚");
		i1.setBuildprice(100);
		for (int i = 0; i < 5; i++)
			tmp[i] = (int) (i1.getBuyprice() * 0.5) * (i+1);
		i1.setPaidmoney(tmp);
		Land j = new Land();
		j.setId(9);
		j.setBuyprice(250);
		j.setItemName("波兰");
		j.setBuildprice(100);
		for (int i = 0; i < 5; i++)
			tmp[i] = (int) (j.getBuyprice() * 0.5) * (i+1);
		j.setPaidmoney(tmp);
		List<Land> neigh3 = new ArrayList<Land>();
		neigh3.add(i1);
		neigh3.add(j);
		h.setNeighbour(neigh3);
		List<Land> neigh4 = new ArrayList<Land>();
		neigh4.add(h);
		neigh4.add(j);
		i1.setNeighbour(neigh4);
		squares.add(h);
		squares.add(i1);
		List<Land> neigh5 = new ArrayList<Land>();
		neigh5.add(h);
		neigh5.add(i1);
		j.setNeighbour(neigh5);
		squares.add(j);
		Square k = new Square();
		k.setId(10);
		squares.add(k);

		Land l = new Land();
		l.setId(11);
		l.setBuyprice(200);
		l.setItemName("俄罗斯");
		l.setBuildprice(100);
		for (int i = 0; i < 5; i++)
			tmp[i] = (int) (l.getBuyprice() * 0.5) * (i+1);
		l.setPaidmoney(tmp);
		Land m = new Land();
		m.setId(12);
		m.setBuyprice(220);
		m.setItemName("乌克兰");
		m.setBuildprice(100);
		for (int i = 0; i < 5; i++)
			tmp[i] = (int) (m.getBuyprice() * 0.5) * (i+1);
		m.setPaidmoney(tmp);
		Land n = new Land();
		n.setId(13);
		n.setBuyprice(250);
		n.setItemName("立陶宛");
		n.setBuildprice(100);
		for (int i = 0; i < 5; i++)
			tmp[i] = (int) (n.getBuyprice() * 0.5) * (i+1);
		n.setPaidmoney(tmp);
		List<Land> neigh6 = new ArrayList<Land>();
		neigh6.add(m);
		neigh6.add(n);
		l.setNeighbour(neigh6);
		List<Land> neigh7 = new ArrayList<Land>();
		neigh7.add(l);
		neigh7.add(n);
		m.setNeighbour(neigh7);
		List<Land> neigh8 = new ArrayList<Land>();
		neigh8.add(l);
		neigh8.add(m);
		n.setNeighbour(neigh8);
		squares.add(l);
		squares.add(m);
		squares.add(n);
		Square o = new Square();
		o.setId(14);
		squares.add(o);
		Square p = new Square();
		p.setId(15);
		squares.add(p);
		Square q = new Square();
		q.setId(16);
		squares.add(q);

		Land r = new Land();
		r.setId(17);
		r.setBuyprice(200);
		r.setItemName("拉脱维亚");
		r.setBuildprice(100);
		for (int i = 0; i < 5; i++)
			tmp[i] = (int) (r.getBuyprice() * 0.5) * (i+1);
		r.setPaidmoney(tmp);
		Land s = new Land();
		s.setId(18);
		s.setBuyprice(220);
		s.setItemName("233");
		for (int i = 0; i < 5; i++)
			tmp[i] = (int) (s.getBuyprice() * 0.5) * (i+1);
		s.setPaidmoney(tmp);
		s.setBuildprice(100);
		Land t = new Land();
		t.setId(19);
		t.setBuyprice(250);
		t.setItemName("爱沙尼亚");
		t.setBuildprice(100);
		for (int i = 0; i < 5; i++)
			tmp[i] = (int) (t.getBuyprice() * 0.5) * (i+1);
		t.setPaidmoney(tmp);
		List<Land> neigh9 = new ArrayList<Land>();
		neigh9.add(s);
		neigh9.add(t);
		r.setNeighbour(neigh9);
		List<Land> neigh10 = new ArrayList<Land>();
		neigh10.add(r);
		neigh10.add(t);
		s.setNeighbour(neigh10);
		List<Land> neigh11 = new ArrayList<Land>();
		neigh11.add(r);
		neigh11.add(s);
		t.setNeighbour(neigh11);
		squares.add(r);
		squares.add(s);
		squares.add(t);

		Square u = new Square();
		u.setId(20);
		squares.add(u);

		Land v = new Land();
		v.setId(21);
		v.setBuyprice(200);
		v.setItemName("挪威");
		v.setBuildprice(100);
		for (int i = 0; i < 5; i++)
			tmp[i] = (int) (v.getBuyprice() * 0.5) * (i+1);
		v.setPaidmoney(tmp);
		Land w = new Land();
		w.setId(22);
		w.setBuyprice(220);
		w.setItemName("瑞典");
		w.setBuildprice(100);
		for (int i = 0; i < 5; i++)
			tmp[i] = (int) (w.getBuyprice() * 0.5) * (i+1);
		w.setPaidmoney(tmp);
		Land x = new Land();
		x.setId(23);
		x.setBuyprice(250);
		x.setItemName("芬兰");
		x.setBuildprice(100);
		for (int i = 0; i < 5; i++)
			tmp[i] = (int) (x.getBuyprice() * 0.5) * (i+1);
		x.setPaidmoney(tmp);
		List<Land> neigh12 = new ArrayList<Land>();
		neigh12.add(w);
		neigh12.add(x);
		v.setNeighbour(neigh12);
		List<Land> neigh13 = new ArrayList<Land>();
		neigh12.add(v);
		neigh12.add(x);
		w.setNeighbour(neigh13);
		List<Land> neigh14 = new ArrayList<Land>();
		neigh13.add(v);
		neigh13.add(w);
		x.setNeighbour(neigh14);
		squares.add(v);
		squares.add(w);
		squares.add(x);

		Square y = new Square();
		y.setId(24);
		squares.add(y);
		Square z = new Square();
		z.setId(25);
		squares.add(z);
		Square zz = new Square();
		y.setId(26);
		squares.add(zz);

		Land aa = new Land();
		aa.setId(27);
		aa.setBuyprice(200);
		aa.setItemName("德国");
		aa.setBuildprice(100);
		for (int i = 0; i < 5; i++)
			tmp[i] = (int) (aa.getBuyprice() * 0.5) * (i+1);
		aa.setPaidmoney(tmp);
		Land bb = new Land();
		bb.setId(28);
		bb.setBuyprice(220);
		bb.setItemName("法国");
		bb.setBuildprice(100);
		for (int i = 0; i < 5; i++)
			tmp[i] = (int) (bb.getBuyprice() * 0.5) * (i+1);
		bb.setPaidmoney(tmp);
		Land cc = new Land();
		cc.setId(29);
		cc.setBuyprice(250);
		cc.setItemName("英国");
		cc.setBuildprice(100);
		for (int i = 0; i < 5; i++)
			tmp[i] = (int) (cc.getBuyprice() * 0.5) * (i+1);
		cc.setPaidmoney(tmp);
		List<Land> neigh15 = new ArrayList<Land>();
		neigh15.add(bb);
		neigh15.add(cc);
		aa.setNeighbour(neigh15);
		List<Land> neigh16 = new ArrayList<Land>();
		neigh16.add(aa);
		neigh16.add(cc);
		bb.setNeighbour(neigh16);
		List<Land> neigh17 = new ArrayList<Land>();
		neigh17.add(aa);
		neigh17.add(bb);
		cc.setNeighbour(neigh17);
		squares.add(aa);
		squares.add(bb);
		squares.add(cc);

		Square dd = new Square();
		dd.setId(30);
		squares.add(dd);

		Land ee = new Land();
		ee.setId(31);
		ee.setBuyprice(200);
		ee.setItemName("加拿大");
		ee.setBuildprice(100);
		for (int i = 0; i < 5; i++)
			tmp[i] = (int) (ee.getBuyprice() * 0.5) * (i+1);
		ee.setPaidmoney(tmp);
		Land ff = new Land();
		ff.setId(32);
		ff.setBuyprice(220);
		ff.setItemName("美国");
		ff.setBuildprice(100);
		for (int i = 0; i < 5; i++)
			tmp[i] = (int) (ff.getBuyprice() * 0.5) * (i+1);
		ff.setPaidmoney(tmp);
		Land gg = new Land();
		gg.setId(33);
		gg.setBuyprice(250);
		gg.setItemName("墨西哥");
		gg.setBuildprice(100);
		for (int i = 0; i < 5; i++)
			tmp[i] = (int) (gg.getBuyprice() * 0.5) * (i+1);
		gg.setPaidmoney(tmp);
		List<Land> neigh18 = new ArrayList<Land>();
		neigh18.add(ff);
		neigh18.add(gg);
		ee.setNeighbour(neigh18);
		List<Land> neigh19 = new ArrayList<Land>();
		neigh19.add(ee);
		neigh19.add(gg);
		ff.setNeighbour(neigh19);
		List<Land> neigh20 = new ArrayList<Land>();
		neigh20.add(ee);
		neigh20.add(gg);
		gg.setNeighbour(neigh20);
		squares.add(ee);
		squares.add(ff);
		squares.add(gg);

		Square hh = new Square();
		hh.setId(34);
		squares.add(hh);
		Square ii = new Square();
		ii.setId(35);
		squares.add(ii);
		Square jj = new Square();
		jj.setId(36);
		squares.add(jj);

		Land kk = new Land();
		kk.setId(27);
		kk.setBuyprice(200);
		kk.setItemName("迪拜");
		kk.setBuildprice(100);
		for (int i = 0; i < 5; i++)
			tmp[i] = (int) (kk.getBuyprice() * 0.5) * (i+1);
		kk.setPaidmoney(tmp);
		Land ll = new Land();
		ll.setId(28);
		ll.setBuyprice(220);
		ll.setItemName("夏威夷");
		ll.setBuildprice(100);
		for (int i = 0; i < 5; i++)
			tmp[i] = (int) (ll.getBuyprice() * 0.5) * (i+1);
		ll.setPaidmoney(tmp);
		Land mm = new Land();
		mm.setId(29);
		mm.setBuyprice(250);
		mm.setItemName("澳大利亚");
		mm.setBuildprice(100);
		for (int i = 0; i < 5; i++)
			tmp[i] = (int) (mm.getBuyprice() * 0.5) * (i+1);
		mm.setPaidmoney(tmp);
		List<Land> neigh21 = new ArrayList<Land>();
		neigh21.add(ll);
		neigh21.add(mm);
		kk.setNeighbour(neigh21);
		List<Land> neigh22 = new ArrayList<Land>();
		neigh22.add(kk);
		neigh21.add(mm);
		ll.setNeighbour(neigh22);
		List<Land> neigh23 = new ArrayList<Land>();
		neigh23.add(kk);
		neigh23.add(ll);
		mm.setNeighbour(neigh23);
		squares.add(kk);
		squares.add(ll);
		squares.add(mm);
		return squares;
	}
}
