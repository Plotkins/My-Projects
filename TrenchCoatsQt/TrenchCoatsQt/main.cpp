#include "Coat.h"
#include "Repository.h"
#include "ShoppingBasket.h"
#include <QtWidgets/QApplication>
#include "TrenchCoatsQt.h"

int main(int argc, char* argv[])
{
	QApplication a(argc, argv);
	Repository repo;
	ShoppingBasket sp;
	Coat c1{ "m", "red",34, 12,120, "http://dimg.dillards.com/is/image/DillardsZoom/04939055_zi_dynamic_red?$ecom308x357$" };
	repo.addCoat(c1);
	Coat c2{ "s", "yellow",99, 100,132, "https://images.asos-media.com/products/asos-classic-trench-coat/7111019-1-stone?$XL$" };
	repo.addCoat(c2);
	Coat c3{ "xl", "black",128, 55,122, "http://g.nordstromimage.com/ImageGallery/store/product/Zoom/10/_11835050.jpg" };
	repo.addCoat(c3);
	Coat c4{ "m", "brown",150, 40,112, "http://www.londonfog.com/media/catalog/product/cache/1/image/9df78eab33525d08d6e5fb8d27136e95/L/F/LF940215_khaki.jpg" };
	repo.addCoat(c4);
	Coat c5{ "m", "white",200, 12,140, "http://img.allw.mn/content/www/2011/05/7-timeless-trench-coats/1_burberry-cotton-blend-trench-coat_7-timeless-trench-coats.jpg" };
	repo.addCoat(c5);
	Controller ctrl{ repo, sp };
	TrenchCoatsQt gui{ ctrl };
	gui.show();
	return a.exec();
}
