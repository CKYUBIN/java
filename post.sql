		INSERT INTO T_reply(id, descrim, bb_id, writer_id, TITLE, content)
		VALUES ('p001', 'post', '000q', '000h', 'dog', 'i like');
		
		INSERT INTO T_reply(id, descrim, bb_id, writer_id, TITLE, content)
		VALUES ('p002', 'post', '000q', '000h', 'cat', 'meow');

		INSERT INTO T_reply(id, descrim, writer_id, content)
		VALUES ('r000', 'reply', '0003' 'meow');

		INSERT INTO T_reply(id, descrim, writer_id, content)
		VALUES (#{id}, 'reply', #{reply.writer.id}, #{reply.content});
		
		INSERT INTO T_comp_hierarch(comp_hierarch, kind)
		VALUES (CONCAT(#{parent.hierarchID}, #{id}),'t2b')
		
		INSERT INTO T_comp_hierarch(comp_hierarch, kind)
		VALUES ('r000', 'p001r000','t2b')