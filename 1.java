-   // reducer		
 -	public static class Reduce extends Reducer<Text, Text, Text, Text> {		
 -		
 -		public void reduce(Text key, Iterable<Text> values, Context context)		
 -				throws IOException, InterruptedException {		
	
 -			double totalCount = 0;		
 -			// initiate the HashMap stripe		
 -			java.util.Map<String, Integer> stripe = new HashMap<>();		
 -		
 -			String keyStr = key.toString();		
 -			String newKey="";		
 -			int count = 0;		
 -		
 -			for (Text value : values) {		
 -				count += Integer.parseInt(value.toString());		
 -			}		
 -		
 -			if (!keyStr.matches(".*\\*")) {	
 -		
 -				String[] pair = keyStr.split(",");		
 -				newKey = pair[0];		
 -				Integer countSum = stripe.get(pair[1]);		
 -				stripe.put(pair[1], (countSum == null ? 0 : countSum) + count);	
	 
	                      
 -		
 -			} else {		
 -		
 -				totalCount = count;// for a certain kind of key,
	                        StringBuilder stripeStr = new StringBuilder();		
 -			for (java.util.Map.Entry entry : stripe.entrySet()) {		
 -		
 -				double d = new Double(entry.getValue().toString()) / totalCount;		
  		  
 -				stripeStr.append(entry.getKey()).append(":")		
 -						.append(String.format("%.2f", d)).append("   ");		
 -			}		
  		  
 -			context.write(new Text(newKey), new Text(stripeStr.toString()));
 -		
 -			}		
 -		
 -			StringBuilder stripeStr = new StringBuilder();		
 -			for (java.util.Map.Entry entry : stripe.entrySet()) {		
 -		
 -				double d = new Double(entry.getValue().toString()) / totalCount;		
  		  
 -				stripeStr.append(entry.getKey()).append(":")		
 -						.append(String.format("%.2f", d)).append("   ");		
 -			}		
  		  
 -			context.write(new Text(newKey), new Text(stripeStr.toString()));		 
 -		}		
