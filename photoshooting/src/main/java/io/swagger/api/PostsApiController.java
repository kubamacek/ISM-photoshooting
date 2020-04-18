package io.swagger.api;

import io.swagger.model.Comment;
import io.swagger.model.Post;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.swagger.annotations.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.*;
import javax.validation.Valid;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.List;
import java.util.Map;

import io.swagger.api.CommentsApiService;

@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen", date = "2020-03-29T16:53:00.885Z[GMT]")
@Controller
public class PostsApiController implements PostsApi {

    private static final Logger log = LoggerFactory.getLogger(PostsApiController.class);

    private final ObjectMapper objectMapper;

    private final HttpServletRequest request;
    
    @Autowired
    private PostsApiService postsApiService;
    
    @Autowired
    private CommentsApiService commentsApiService;

    @org.springframework.beans.factory.annotation.Autowired
    public PostsApiController(ObjectMapper objectMapper, HttpServletRequest request) {
        this.objectMapper = objectMapper;
        this.request = request;
    }

    public ResponseEntity<String> addPost(@ApiParam(value = "Post object that needs to be created" ,required=true )  @Valid @RequestBody Post body
) {
    	if (body.validateAllFields() == false){
    		return new ResponseEntity<String>("Invalid request",HttpStatus.BAD_REQUEST);
    	}
        String msg = postsApiService.addPost(body);
        return new ResponseEntity<String>(msg, HttpStatus.OK);
    }

    public ResponseEntity<String> addPostComment(@ApiParam(value = "Comment object that needs to be created" ,required=true )  @Valid @RequestBody Comment body
,@Min(1)@ApiParam(value = "Post id",required=true, allowableValues="") @PathVariable("id") Integer id
) {
        if (body.validateAllFields() == false) {
        	return new ResponseEntity<String>("Invalid request",HttpStatus.BAD_REQUEST);
        }
        else {
        	if (postsApiService.checkIfExists(id) == true) {
        		Post post = postsApiService.getPostbyId(id);
                body.setPost(post);
                String msg = commentsApiService.addPostComment(body);
                return new ResponseEntity<String>(msg, HttpStatus.OK);
        	}
        	else {
        		return new ResponseEntity<String>("Post not found", HttpStatus.NOT_FOUND);
        	}
        }
    }

    public ResponseEntity<String> deletePost(@Min(1)@ApiParam(value = "Post id",required=true, allowableValues="") @PathVariable("id") Integer id
) {
        if (postsApiService.checkIfExists(id) == true) {
        	String msg = postsApiService.deletePost(id);
            return new ResponseEntity<String>(msg, HttpStatus.OK);
        }
        else {
        	return new ResponseEntity<String>("Post not found", HttpStatus.NOT_FOUND);
        }
    }

    public ResponseEntity<String> deletePostComment(@Min(1)@ApiParam(value = "Post id",required=true, allowableValues="") @PathVariable("postId") Integer postId
,@Min(1)@ApiParam(value = "Comment id",required=true, allowableValues="") @PathVariable("commentId") Integer commentId
) {
        if (commentsApiService.checkIfExists(commentId) == true) {
        	String msg = commentsApiService.deletePostComment(postId, commentId);
            return new ResponseEntity<String>(msg, HttpStatus.OK);
        }
        else {
        	return new ResponseEntity<String>("Comment not found", HttpStatus.NOT_FOUND);
        }
    }

    public ResponseEntity<String> editPostComment(@ApiParam(value = "Comment object that needs to be updated" ,required=true )  @Valid @RequestBody Comment body
,@Min(1)@ApiParam(value = "Post id",required=true, allowableValues="") @PathVariable("postId") Integer postId
,@Min(1)@ApiParam(value = "Comment id",required=true, allowableValues="") @PathVariable("commentId") Integer commentId
) {
    	if (body.validateAllFields() == true) {
    		if (commentsApiService.checkIfExists(commentId) == true) {
        		String msg = commentsApiService.editPostComment(postId, commentId, body);
                return new ResponseEntity<String>(msg, HttpStatus.OK);
        	}
        	else {
        		return new ResponseEntity<String>("Comment not found", HttpStatus.NOT_FOUND);
        	}
    	}
    	else {
    		return new ResponseEntity<String>("Invalid request", HttpStatus.BAD_REQUEST);
    	}
    }

    public ResponseEntity<Post> getPost(@Min(1)@ApiParam(value = "Post id",required=true, allowableValues="") @PathVariable("id") Integer id
) {
        if (postsApiService.checkIfExists(id) == true) {
        	Post post = postsApiService.getPostbyId(id);
            return new ResponseEntity<Post>(post, HttpStatus.OK);
        }
        else {
        	return new ResponseEntity<Post>(HttpStatus.NOT_FOUND);        	
        }
    }

    public ResponseEntity<Comment> getPostComment(@Min(1)@ApiParam(value = "Post id",required=true, allowableValues="") @PathVariable("postId") Integer postId
,@Min(1)@ApiParam(value = "Comment id",required=true, allowableValues="") @PathVariable("commentId") Integer commentId
) {
    	if (commentsApiService.checkIfExists(commentId) == true) {
    		Comment comment = commentsApiService.getPostComment(postId, commentId);
            return new ResponseEntity<Comment>(comment, HttpStatus.OK);
    	}
    	else {
    		Comment comment = commentsApiService.getPostComment(postId, commentId);
            return new ResponseEntity<Comment>(HttpStatus.NOT_FOUND);    		
    	}
    }

    public ResponseEntity<List<Comment>> getPostComments(@Min(1)@ApiParam(value = "Post id",required=true, allowableValues="") @PathVariable("id") Integer id
) {
        if (postsApiService.checkIfExists(id) == true) {
        	List<Comment> comments = commentsApiService.getPostComments(id);
            return new ResponseEntity<List<Comment>>(comments, HttpStatus.OK);
        }
        else {
        	return new ResponseEntity<List<Comment>>(HttpStatus.NOT_FOUND);
        }
    }

    public ResponseEntity<List<Post>> getPosts() {
        List<Post> posts = postsApiService.getPosts();
        return new ResponseEntity<List<Post>>(posts, HttpStatus.OK);
    }

    public ResponseEntity<String> likePost(@Min(1)@ApiParam(value = "Post id",required=true, allowableValues="") @PathVariable("id") Integer id
) {
        String msg = postsApiService.likePost(id);
        return new ResponseEntity<String>(msg, HttpStatus.OK);
    }

    public ResponseEntity<String> updatePost(@Min(1)@ApiParam(value = "Post id",required=true, allowableValues="") @PathVariable("id") Integer id
,@ApiParam(value = "Post object that needs to be updated"  )  @Valid @RequestBody Post body
) {
        if (body.validateAllFields() == true) {
        	if (postsApiService.checkIfExists(id)) {
        		String msg = postsApiService.updatePost(id, body);
                return new ResponseEntity<String>(msg, HttpStatus.OK);
        	}
        	else {
        		return new ResponseEntity<String>("Post not found", HttpStatus.NOT_FOUND);
        	}
        }
        else {
        	return new ResponseEntity<String>("Invalid request", HttpStatus.BAD_REQUEST);
        }
    }

}
