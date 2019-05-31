package course;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.bson.Document;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import com.mongodb.client.model.Sorts;

public class BlogPostDAO {
	MongoCollection<Document> postsCollection;

	public BlogPostDAO(final MongoDatabase blogDatabase) {
		this.postsCollection = blogDatabase.getCollection("posts");
	}

	// Return a single post corresponding to a permalink
	public Document findByPermalink(String permalink) {
		// todo  XXX - done
		Document post = null;
		// author, body, permalink, tags, comments, date
		post=this.postsCollection.find(Filters.eq("permalink", permalink)).first();
		return post;
	}

	// Return a list of posts in descending order. Limit determines
	// how many posts are returned.
	public List<Document> findByDateDescending(int limit) {
		// todo,  XXX - done
		// Return a list of Documents, each one a post from the posts collection
		List<Document> posts = new ArrayList<Document>();
		this.postsCollection.find().limit(limit).sort(Sorts.descending("date")).into(posts);
		return posts;
	}


	public String addPost(String title, String body, List<String> tags, String username) {

		System.out.println("inserting blog entry " + title + " " + body);

		Date currentDate=new Date();

		String permalink = title.replaceAll("\\s", "_"); // whitespace becomes _
		permalink = permalink.replaceAll("\\W", ""); // get rid of non alphanumeric
		permalink = permalink.toLowerCase();
		permalink = permalink+ currentDate.getTime();


		// todo XXX - done
		// Remember that a valid post has the following keys:
		// author, body, permalink, tags, comments, date
		//
		// A few hints:
		// - Don't forget to create an empty list of comments
		// - for the value of the date key, today's datetime is fine.
		// - tags are already in list form that implements suitable interface.
		// - we created the permalink for you above.

		Document post=new Document();

		post.append("title", title);
		post.append("author", username);
		post.append("body", body);
		post.append("permalink", permalink);
		post.append("tags", tags);
		post.append("comments", new ArrayList<Document>());
		post.append("date", currentDate);

		this.postsCollection.insertOne(post);

		return permalink;
	}




	// White space to protect the innocent








	// Append a comment to a blog post
	public void addPostComment(final String name, final String email, final String body,
			final String permalink) {

		// todo  XXX - done
		// Hints:
		// - email is optional and may come in NULL. Check for that.
		// - best solution uses an update command to the database and a suitable
		//   operator to append the comment on to any existing list of comments

		Document document=this.findByPermalink(permalink);
		List<Document> comments=(List<Document>)document.get("comments");
		Document newComment=new Document();
		newComment.append("author", name);
		if(email!=null){
			newComment.append("email", email);
		}
		newComment.append("body", body);

		comments.add(newComment);

		this.postsCollection.replaceOne(new Document("_id", document.get("_id")), document);

	}
}
