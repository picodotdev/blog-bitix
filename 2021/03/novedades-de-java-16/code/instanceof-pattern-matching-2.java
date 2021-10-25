// Antes
public boolean equals(Object o) {
   if (!(o instanceof Point))
       return false;
   Point other = (Point) o;
   return x == other.x
       && y == other.y;
}

// Ahora, con pattern matching
public boolean equals(Object o) {
   return (o instanceof Point other)
       && x == other.x
       && y == other.y;
}