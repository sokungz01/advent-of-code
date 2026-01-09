package year2025.day02;

import java.util.*;

public class Giftshop {
    public static void main(String[] args){
        List<ProductIdRange> productIdRangesList = getProductIdRangeList();

        for(ProductIdRange productIdRange : productIdRangesList){
            System.out.printf("%d %d\n",productIdRange.startId,productIdRange.endId);
        }

        List<Long> invalidProductIds = getInvalidProductIdList(productIdRangesList);

        for(Long productId : invalidProductIds){
            System.out.printf("%d\n",productId);
        }

        Long sumOfInvalidProductIds = invalidProductIds.stream().reduce(0L,Long::sum);
        System.out.println("Sum of valid product ids: "+sumOfInvalidProductIds);
    }

    public static List<ProductIdRange> getProductIdRangeList(){
        List<ProductIdRange> productIdRangesList = new ArrayList<>();
        Scanner scanner = new Scanner(System.in);
        String inputLine = scanner.nextLine().trim();
        String[] rangeList = inputLine.split(",");

        for(String range : rangeList){
            String[] numbers =  range.split("-");
            ProductIdRange productIdRange = new ProductIdRange();
            productIdRange.startId = Long.parseLong(numbers[0].trim());
            productIdRange.endId = Long.parseLong(numbers[1].trim());
            productIdRangesList.add(productIdRange);
        }

        return productIdRangesList;
    }

    public static List<Long> getInvalidProductIdList(List<ProductIdRange> productIdRangesList){
        Set<Long> invalidProductIdsSet = new TreeSet<>();
        for(ProductIdRange productIdRange : productIdRangesList){
            long start = productIdRange.startId;
            long end = productIdRange.endId;

            for(long pow = 10L; pow > 0 && pow <= Long.MAX_VALUE / 10L; pow *= 10L){
                long lowerX = pow / 10L;
                long upperX = pow - 1L;

                long denom = pow + 1L;
                long minX = (start + denom - 1) / denom;
                long maxX = end / denom;

                if(minX < lowerX) minX = lowerX;
                if(maxX > upperX) maxX = upperX;
                if(minX > maxX) continue;

                for(long x = minX; x <= maxX; x++){
                    long concat = x * denom;
                    if(concat >= start && concat <= end){
                        invalidProductIdsSet.add(concat);
                    }
                }

                if(lowerX * denom > end) break;
            }
        }

        return new ArrayList<>(invalidProductIdsSet);
    }
}